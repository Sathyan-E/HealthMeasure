package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.healthykid.healthbasicmeasure.modelclass.Measurement
import com.healthykid.healthbasicmeasure.repository.DataEntryActivityRepository

class DataEntryActivityViewModel(application: Application) : AndroidViewModel(application) {
    private  val repository=DataEntryActivityRepository(application)
    val updateSuccess:LiveData<Boolean>

    init {
        this.updateSuccess=repository.updateSuccess

    }


    fun updateDetails(data:Measurement,ref:String){
        repository.updateDetails(data,ref)
    }

}