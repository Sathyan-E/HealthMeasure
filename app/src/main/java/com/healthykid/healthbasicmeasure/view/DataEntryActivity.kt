package com.healthykid.healthbasicmeasure.view

import android.content.Context
import android.content.Intent
import android.icu.util.Measure
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.modelclass.Measurement
import com.healthykid.healthbasicmeasure.viewmodel.DataEntryActivityViewModel
import kotlinx.android.synthetic.main.activity_data_entry.*
import kotlinx.android.synthetic.main.activity_fetch.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataEntryActivity : AppCompatActivity() {
    private var studentId:String=""
    private var studentName:String=""
    private var sWeight:String=""
    private var sHeight:String=""
    private var sSystol:String=""
    private var sDiastol:String=""
    private var sTemp:String=""
    private var sPulse:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)
        val viewmdel=ViewModelProvider(this).get(DataEntryActivityViewModel::class.java)

      //  ref=FirebaseDatabase.getInstance().reference.child("BasicHealthInfo").child("999-9999-9991")
        studentId=intent.getStringExtra("id").toString()
        studentName=intent.getStringExtra("name").toString()

        viewmdel.updateSuccess.observe(this, Observer {
            if (it==true){

                val i =Intent(this,ResultActivity::class.java)
                i.putExtra("name",studentName)
                i.putExtra("id",studentId)
                startActivity(i)
            }else{
                Toast.makeText(this,"Problem while uploading data.Please Try Again!",Toast.LENGTH_SHORT).show()
            }
        })


        upload_details_btn.setOnClickListener {
         if (checkInternet()){
                val isValid=validation()
                if (isValid){
                    val sBloodPressure= "$sSystol/$sDiastol"
                    val measure=Measurement(sHeight,sWeight,sBloodPressure,sTemp,sPulse)
                    viewmdel.updateDetails(measure, studentId)
                }
            }else{
            dataentryLayout.visibility=GONE
            dataentry_internet_layout.visibility= VISIBLE
            refresh()
            Toast.makeText(this,"Turn on Your Network Connection",Toast.LENGTH_SHORT).show()
        }


        }
        dataentry_refresh_btn.setOnClickListener {
            refresh()
        }
    }

    fun validation():Boolean{
        //Weight validation
        var isWeightValid=false
        var isHeightValid=false
        var isTempValid=false
        var isPulseValid=false
        var isSystolValid=false
        var isDiastolValid=false
      //  val isWeightValid=false
        if (weight_entry_et.text.toString().isNotEmpty()){
            if (weight_entry_et.text.toString()=="-"){
                sWeight="-"
                isWeightValid=true
            }else if (weight_entry_et.text.toString().toInt() in (1..200)){
                sWeight=weight_entry_et.text.toString()
                isWeightValid=true
            }
            else{
                weight_entry_et.error="Invalid Input"
                isWeightValid=false
            }

        }
        else{
            isWeightValid=false
            weight_entry_et.error="Enter Value"
        }
        //Height Validation
        if (height_entry_et.text.toString().isNotEmpty() ){
            if (height_entry_et.text.toString()=="-"){
                isHeightValid=true
                sHeight="-"
            }else if(height_entry_et.text.toString().toInt() in (1..250)){
                isHeightValid=true
                sHeight=height_entry_et.text.toString()
            }else{
                isHeightValid=false
                height_entry_et.error="Invalid input"
            }

        }
        else{
            isHeightValid=false
            height_entry_et.error="Enter Value"
        }
        //Systol Validation
        if (cistol_et.text.toString().isNotEmpty()){
            if (cistol_et.text.toString()=="-"){
                isSystolValid=true
                sSystol="-"
            }else if(cistol_et.text.toString().toInt() in (1..200)){
                isSystolValid=true
                sSystol=cistol_et.text.toString()
            }else{
                isSystolValid=false
                cistol_et.error="Invalid  Input"
            }
        }
        else{
            isSystolValid=false
            cistol_et.error="Enter Value"
        }
        //Diastol Validation
        if (diastol_et.text.toString().isNotEmpty()){
            if (diastol_et.text.toString()=="-"){
                isDiastolValid=true
                sDiastol="-"
            }else if(diastol_et.text.toString().toInt() in (1..200)){
                isDiastolValid=true
                sDiastol=diastol_et.text.toString()
            }else{
                isDiastolValid=false
                diastol_et.error="Invalid Input"
            }
        }
        else{
            isDiastolValid=false
            diastol_et.error="Enter Value"
        }
        //Temperature Validation
        if (temperature_entry_et.text.toString().isNotEmpty()){
            if (temperature_entry_et.text.toString()=="-"){
                isTempValid=true
                sTemp="-"
            }else if (temperature_entry_et.text.toString().toInt() in 1..200){
                isTempValid=true
                sTemp=temperature_entry_et.text.toString()
            }else{
                isTempValid=false
                temperature_entry_et.error="Invalid Input"
            }
        }
        else{
            isTempValid=false
            temperature_entry_et.error="Enter Value"
        }
        //Pulse Validation
        if (pulse_entry_et.text.toString().isNotEmpty()){
            if (pulse_entry_et.text.toString()=="-"){
                isPulseValid=true
                   sPulse="-"
            }else if(pulse_entry_et.text.toString().toInt() in (1..200)){
                isPulseValid=true
                sPulse=pulse_entry_et.text.toString()
            }else{
                isPulseValid=false
                pulse_entry_et.error="InValid Input"
            }
        }
        else{
            isPulseValid=false
            pulse_entry_et.error="Enter Value"
        }

        if(isWeightValid && isHeightValid && isPulseValid && isSystolValid && isDiastolValid && isTempValid){
            return  true
        }
        return  false
    }
    private fun checkInternet():Boolean{
        val connectManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=connectManager.activeNetworkInfo
        val isConected=activeNetwork?.isConnectedOrConnecting == true
        return isConected
    }
    private fun refresh(){
        if (checkInternet()){
            dataentryLayout.visibility= VISIBLE
            dataentry_internet_layout.visibility= INVISIBLE

        }else{
            Toast.makeText(this,"No InternetConnection!",Toast.LENGTH_SHORT).show()
        }
    }
}