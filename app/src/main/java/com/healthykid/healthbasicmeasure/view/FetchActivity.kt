package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.FetchActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_fetch.*

class FetchActivity : AppCompatActivity() {
    private lateinit var viewModel:FetchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        viewModel=ViewModelProvider(this).get(FetchActivityViewModel::class.java)

        viewModel.studentDetails.observe(this, Observer {
            fetch_progressbar.visibility== INVISIBLE
            val intent=Intent(this,DetailsActivity::class.java)
            intent.putExtra("basicdetails",it)
            startActivity(intent)

        })

        viewModel.isValidUhid.observe(this, Observer {
            if (it==false){
               fetch_progressbar.visibility= INVISIBLE
                student_uhid_ed.error="UhId is not valid."
            }

        })


        fetch_details_btn.setOnClickListener {

            val uhId=student_uhid_ed.text.toString()
            if (uhId.length>11){
                fetch_progressbar.visibility=VISIBLE
                viewModel.getStudentDetails(uhId)

            }else{
                student_uhid_ed.error="Enter valid UhId"
            }

        }
    }
}