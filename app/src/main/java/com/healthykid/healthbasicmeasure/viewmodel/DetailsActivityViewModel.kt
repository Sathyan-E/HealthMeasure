package com.healthykid.healthbasicmeasure.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.healthykid.healthbasicmeasure.modelclass.BasicDetails
import com.healthykid.healthbasicmeasure.repository.DetailsActivityRepository

class DetailsActivityViewModel(application: Application): AndroidViewModel(application) {
   private val repository=DetailsActivityRepository(application)
   val showProgress:LiveData<Boolean>
   val studentDetails:LiveData<BasicDetails>

   init {
       this.studentDetails=repository.studentDetails
       this.showProgress=repository.showProgress
   }
   
   fun getStudentDetails(id:String){
         repository.getStudentDetails(id)
   }
}