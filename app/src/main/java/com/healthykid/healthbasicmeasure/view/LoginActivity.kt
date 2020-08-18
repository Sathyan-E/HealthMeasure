package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var userName:String=""
    var userPassword:String=""
    lateinit var viewmodel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewmodel=ViewModelProvider(this).get(LoginViewModel::class.java)

        viewmodel.isUserExisted.observe(this, Observer {
            if(it==true){
                goNextScreen()
            }
        })

        viewmodel.loginSuccess.observe(this, Observer {
            login_progressbar.visibility= INVISIBLE
            if(it=="success"){
                //viewmodel.saveuserCredentials(userName,userPassword)
                viewmodel.saveUser.observe(this, Observer {
                    if (it==true){
                        goNextScreen()
                    }
                })

            }else if(it=="password"){
                login_password_et.error = "Wrong Password"
            }
            else{
                login_username_et.error="Wrong User Name"

            }
        })

        login_btn.setOnClickListener{
            login_progressbar.visibility=VISIBLE
            userName=login_username_et.text.toString()
            userPassword=login_password_et.text.toString()
            viewmodel.checkCredentials(userName,userPassword)
        }
    }

    override fun onResume() {
        super.onResume()
        viewmodel.checkExistedUser()
    }

    fun goNextScreen(){
        val intent=Intent(this,FetchActivity::class.java)
        startActivity(intent)

    }


}