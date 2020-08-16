package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthykid.healthbasicmeasure.R
import kotlinx.android.synthetic.main.activity_data_entry.*

class DataEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)

        upload_details_btn.setOnClickListener {
            val i=Intent(this,ResultActivity::class.java)
            startActivity(i)
        }
    }
}