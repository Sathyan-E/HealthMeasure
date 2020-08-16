package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthykid.healthbasicmeasure.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener{
            val intent=Intent(this,FetchActivity::class.java)
            startActivity(intent)
        }
    }
}