package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.modelclass.BasicDetails
import com.healthykid.healthbasicmeasure.viewmodel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel:DetailsActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val sid=intent.getStringExtra("id")

        viewModel=ViewModelProvider(this).get(DetailsActivityViewModel::class.java)
        viewModel.getStudentDetails(sid!!)

        viewModel.showProgress.observe(this , Observer {
            if (it){

            }
            else{

            }
        })

        viewModel.studentDetails.observe(this, Observer {
            updateUi(it)
           // student_name.text=it.sName
            Toast.makeText(this,it.sRollNumber,Toast.LENGTH_SHORT).show()
        })

/**
        student_name.text=sName

        student_blood.text=intent.getStringExtra("bg")
        student_class.text=intent.getStringExtra("class")
        student_dob.text=intent.getStringExtra("dob")
        student_father.text=intent.getStringExtra("fathername")
        student_mother.text=intent.getStringExtra("mothername")
        student_genere.text=intent.getStringExtra("sex")
        student_rollno.text=intent.getStringExtra("rollno")
**/

        confirm_btn.setOnClickListener {
            val i=Intent(this,DataEntryActivity::class.java)
            startActivity(i);
        }
    }


    fun updateUi(x:BasicDetails){


    }
}