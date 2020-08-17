package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.healthykid.healthbasicmeasure.repository.LoginActivityRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {
   private val repository=LoginActivityRepository(application)
}