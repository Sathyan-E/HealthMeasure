package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.healthykid.healthbasicmeasure.repository.LoginActivityRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {
   private val repository=LoginActivityRepository(application)
   val loginSuccess:LiveData<String>
   val isUserExisted:LiveData<Boolean>
   val saveUser:LiveData<Boolean>
   init {
       this.loginSuccess=repository.loginSucccess
      this.isUserExisted=repository.isExistingUser
      this.saveUser=repository.isUserSaved
   }

   fun checkCredentials(user:String,password:String){
      repository.checkCredentials(user,password)
   }
   fun checkExistedUser(){
      repository.checkExistingUser()
   }

   fun saveuserCredentials(name:String,password:String){
         repository.saveUserCredentials(name,password)
   }
}