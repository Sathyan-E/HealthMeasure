package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import android.icu.util.Measure
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.modelclass.Measurement
import com.healthykid.healthbasicmeasure.viewmodel.DataEntryActivityViewModel
import kotlinx.android.synthetic.main.activity_data_entry.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataEntryActivity : AppCompatActivity() {
    private lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)
        val viewmdel=ViewModelProvider(this).get(DataEntryActivityViewModel::class.java)

        ref=FirebaseDatabase.getInstance().reference.child("BasicHealthInfo").child("999-9999-9991")

        viewmdel.updateSuccess.observe(this, Observer {
            if (it==true){
                val i =Intent(this,ResultActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(this,"Problem while uploading data.Please Try Again!",Toast.LENGTH_SHORT).show()
            }
        })


        upload_details_btn.setOnClickListener {
            val sWeight=weight_entry_et.text.toString()
            val sHeight=height_entry_et.text.toString()
            val sBloodPressure=bpressure_entry_et.text.toString()
            val sTemperature=temperature_entry_et.text.toString()
            val sPulse=pulse_entry_et.text.toString()

            val measure=Measurement(sHeight,sWeight,sBloodPressure,sTemperature,sPulse)
            val id=intent.getStringExtra("id")
            viewmdel.updateDetails(measure,id!!)

        }
    }
}