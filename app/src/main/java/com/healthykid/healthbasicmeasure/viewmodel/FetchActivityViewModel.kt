package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.healthykid.healthbasicmeasure.modelclass.BasicDetails
import com.healthykid.healthbasicmeasure.repository.DetailsActivityRepository

class FetchActivityViewModel(application: Application): AndroidViewModel(application) {
   private val repository=DetailsActivityRepository(application)
   val showProgress:LiveData<Boolean>
   val studentDetails:LiveData<BasicDetails>
    val isValidUhid:LiveData<Boolean>

   init {
       this.studentDetails=repository.studentDetails
       this.showProgress=repository.showProgress
       this.isValidUhid=repository.isValidUhID
   }
   
   fun getStudentDetails(id:String){
         repository.getStudentDetails(id)
   }
}