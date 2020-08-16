package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.healthykid.healthbasicmeasure.repository.DataEntryActivityRepository

class DataEntryActivityViewModel(application: Application) : AndroidViewModel(application) {
    private  val repository=DataEntryActivityRepository(application)
}