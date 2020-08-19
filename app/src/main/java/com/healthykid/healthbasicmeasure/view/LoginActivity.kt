package com.healthykid.healthbasicmeasure.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var userName:String=""
    var userPassword:String=""
    var userErrorMessage:String=""
    var passwordErrorMesssage=""
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
                        usernmae_error_tv.visibility= INVISIBLE
                        password_error_tv.visibility= INVISIBLE
                        goNextScreen()
                    }
                })

            }else if(it=="password"){
                passwordErrorMesssage="Password is Wrong!"
                password_error_tv.text=passwordErrorMesssage
                password_error_tv.visibility= VISIBLE
                login_password_et.error = "Wrong Password"
            }
            else{
                userErrorMessage="User Name is Wrong!"
                usernmae_error_tv.text=userErrorMessage
                usernmae_error_tv.visibility= VISIBLE
                login_username_et.error="Wrong User Name"

            }
        })

        login_btn.setOnClickListener{
            if (checkInternet()){
                login_progressbar.visibility=VISIBLE
                if (login_username_et.text.isNotEmpty() && login_password_et.text.isNotEmpty()){
                    usernmae_error_tv.visibility= INVISIBLE
                    password_error_tv.visibility= INVISIBLE
                    userName=login_username_et.text.toString()
                    userPassword=login_password_et.text.toString()
                    viewmodel.checkCredentials(userName,userPassword)
                }else if(login_username_et.text.isNotEmpty() && !login_password_et.text.isNotEmpty()){
                    passwordErrorMesssage="Enter your Password here"
                    password_error_tv.text=passwordErrorMesssage
                    password_error_tv.visibility= VISIBLE
                }else{
                    userErrorMessage="Enter your username here"
                    usernmae_error_tv.text=userErrorMessage
                    usernmae_error_tv.visibility= VISIBLE
                }

            }else{
                Toast.makeText(this,"Turn on your InternetConnection!",Toast.LENGTH_SHORT).show()
            }

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
    private fun checkInternet():Boolean{
        val connectManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=connectManager.activeNetworkInfo
        val isConected=activeNetwork?.isConnectedOrConnecting == true
        return isConected
    }


}