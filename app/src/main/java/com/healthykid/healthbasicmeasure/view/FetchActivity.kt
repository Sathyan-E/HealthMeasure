package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthykid.healthbasicmeasure.R
import kotlinx.android.synthetic.main.activity_fetch.*

class FetchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        fetch_details_btn.setOnClickListener {
            val intent=Intent(this,DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}