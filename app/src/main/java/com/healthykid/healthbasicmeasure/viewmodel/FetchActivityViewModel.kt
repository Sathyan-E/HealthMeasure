package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.healthykid.healthbasicmeasure.repository.FetchActivityRepository

class FetchActivityViewModel(application: Application): AndroidViewModel(application) {
    val respository=FetchActivityRepository(application)
}