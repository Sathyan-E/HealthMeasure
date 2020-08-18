package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthykid.healthbasicmeasure.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val id=intent.getStringExtra("id")
        val name=intent.getStringExtra("name")
        val result="The details of ${name} - ${id} is uploaded to our database successfully.You can move on to next student."
        result_tv.text=result
        next_btn.setOnClickListener {
            val i=Intent(this,FetchActivity::class.java)
            startActivity(i)
        }
    }
}