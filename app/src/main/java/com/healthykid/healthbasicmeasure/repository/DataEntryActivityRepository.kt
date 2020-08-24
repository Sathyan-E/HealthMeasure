package com.healthykid.healthbasicmeasure.repository

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.healthykid.healthbasicmeasure.modelclass.Measurement
import com.healthykid.healthbasicmeasure.view.ResultActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DataEntryActivityRepository(application: Application) {
    val updateSuccess=MutableLiveData<Boolean>()

    fun updateDetails(data:Measurement,ref:String){
        val databaseReference= FirebaseDatabase.getInstance().reference.child("BasicHealthInfo").child(ref)
       /**
        val current= LocalDateTime.now()
        val formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted=current.format(formatter)
        **/
        val dateFormat=SimpleDateFormat("dd-MM-yyyy")
        dateFormat.timeZone= TimeZone.getTimeZone("IST")
        val today=Calendar.getInstance().time
        val formatted=dateFormat.format(today)

        databaseReference.child(formatted).setValue(data, DatabaseReference.CompletionListener { error, ref ->
            updateSuccess.value = error==null
        })



    }
}