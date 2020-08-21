package com.healthykid.healthbasicmeasure.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class
LoginActivity : AppCompatActivity() {
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
            else if (it=="username"){
                userErrorMessage="User Name is Wrong!"
                usernmae_error_tv.text=userErrorMessage
                usernmae_error_tv.visibility= VISIBLE
                login_username_et.error="Wrong User Name"
            }else{
                userErrorMessage="User Name is Wrong!"
                passwordErrorMesssage="Password is Wrong!"
                login_username_et.error="Username is Wrong"
                login_password_et.error="Password is Wrong"
                password_error_tv.text=passwordErrorMesssage
                usernmae_error_tv.text=userErrorMessage
                usernmae_error_tv.visibility= VISIBLE
                password_error_tv.visibility= VISIBLE
            }
        })

        login_btn.setOnClickListener{
            hideKeyboard()
            if (checkInternet()){

                if (login_username_et.text!!.isNotEmpty() && login_password_et.text!!.isNotEmpty()){
                    login_progressbar.visibility=VISIBLE
                    usernmae_error_tv.visibility= INVISIBLE
                    password_error_tv.visibility= INVISIBLE
                    userName=login_username_et.text.toString()
                    userPassword=login_password_et.text.toString()
                    viewmodel.checkCredentials(userName,userPassword)
                }else if(login_username_et.text!!.isNotEmpty() && !login_password_et.text!!.isNotEmpty()){
                    passwordErrorMesssage="Enter your Password here"
                    password_error_tv.text=passwordErrorMesssage
                    password_error_tv.visibility= VISIBLE
                }else if(!login_username_et.text!!.isNotEmpty() && login_password_et.text!!.isNotEmpty()){
                    userErrorMessage="Enter your username here"
                    usernmae_error_tv.text=userErrorMessage
                    usernmae_error_tv.visibility= VISIBLE
                }else{
                    userErrorMessage="Enter Your Username here!"
                    passwordErrorMesssage="Enter Your Password here!"
                    usernmae_error_tv.text=userErrorMessage
                    password_error_tv.text=passwordErrorMesssage
                    usernmae_error_tv.visibility= VISIBLE
                    password_error_tv.visibility= VISIBLE
                }

            }else{
                loginLayout.visibility= GONE
                internet_layout.visibility= VISIBLE
                Toast.makeText(this,"Turn on your InternetConnection!",Toast.LENGTH_SHORT).show()
            }

        }

        login_refresh_btn.setOnClickListener {
            refresh()
        }

        login_username_et.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               usernmae_error_tv.visibility= GONE
            }

        })

        login_password_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              password_error_tv.visibility= GONE
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewmodel.checkExistedUser()
    }

    private fun goNextScreen(){
        val intent=Intent(this,FetchActivity::class.java)
        startActivity(intent)
        finish()

    }
    private fun checkInternet():Boolean{
        val connectManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=connectManager.activeNetworkInfo
        val isConected=activeNetwork?.isConnectedOrConnecting == true
        return isConected
    }
    private fun refresh(){
        if (checkInternet()){
            loginLayout.visibility= VISIBLE
            internet_layout.visibility= INVISIBLE
        }else{
            Toast.makeText(this,"No InternetConnection!",Toast.LENGTH_SHORT).show()
        }
    }
    fun hideKeyboard(){
        val view=this.currentFocus
        view?.let {
            val v=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            v?.hideSoftInputFromWindow(it.windowToken,0)
        }
    }


}