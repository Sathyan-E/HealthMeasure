package com.healthykid.healthbasicmeasure.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivityRepository(val application: Application) {
    val loginSucccess=MutableLiveData<String>()
    val isExistingUser=MutableLiveData<Boolean>()
    val isUserSaved=MutableLiveData<Boolean>()
    var userName=""
    var userPassword=""
    fun checkCredentials(user:String,password:String){
        val ref=FirebaseDatabase.getInstance().reference.child("credentials")
        ref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
               if (snapshot.exists()){
                   userName=snapshot.child("user").value.toString()
                   userPassword=snapshot.child("password").value.toString()
                   if(user == userName && password == userPassword){
                       val sharedPreferences=application.getSharedPreferences("UserDetails",Context.MODE_PRIVATE)
                       val editor=sharedPreferences.edit()
                       editor.putString("user",userName)
                       editor.putString("password",userPassword)
                       editor.apply()
                       isUserSaved.value = sharedPreferences.contains("user") && sharedPreferences.contains("password")
                       loginSucccess.value="success"
                   }
                   else if(user == userName && password != userPassword){
                       loginSucccess.value="password"
                   }
                   else{
                       loginSucccess.value="username"
                   }


               }
            }

        })
    }

    fun  checkExistingUser(){
        val sharedPreference:SharedPreferences=application.getSharedPreferences("UserDetails",Context.MODE_PRIVATE)
        isExistingUser.value = sharedPreference.contains("user")
    }
    fun saveUserCredentials(userName:String,userPassword:String){

    }

}