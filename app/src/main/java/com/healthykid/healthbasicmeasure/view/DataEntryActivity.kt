package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import android.icu.util.Measure
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.modelclass.Measurement
import kotlinx.android.synthetic.main.activity_data_entry.*

class DataEntryActivity : AppCompatActivity() {
    private lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)

        ref=FirebaseDatabase.getInstance().reference.child("BasicHealthInfo").child("999-9999-9991")




        upload_details_btn.setOnClickListener {
            val sWeight=weight_entry_et.text.toString()
            val sHeight=height_entry_et.text.toString()
            val sBloodPressure=bpressure_entry_et.text.toString()
            val sTemperature=temperature_entry_et.text.toString()
            val sPulse=pulse_entry_et.text.toString()

            val measure=Measurement(sHeight,sWeight,sBloodPressure,sTemperature,sPulse)
            ref.child("member1").setValue(measure)

            val i=Intent(this,ResultActivity::class.java)
            //startActivity(i)
        }
    }
}