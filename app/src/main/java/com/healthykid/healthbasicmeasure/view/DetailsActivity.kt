package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.modelclass.BasicDetails
import com.healthykid.healthbasicmeasure.viewmodel.FetchActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel:FetchActivityViewModel
    var studentName:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val sid=intent.getStringExtra("id")

        viewModel=ViewModelProvider(this).get(FetchActivityViewModel::class.java)
        //viewModel.getStudentDetails(sid!!)
        val details=intent.getParcelableExtra<BasicDetails>("basicdetails")
        updateUi(details!!)

        confirm_btn.setOnClickListener {
            val uhID=intent.getStringExtra("id")
            val i=Intent(this,DataEntryActivity::class.java)
            i.putExtra("id",uhID)
            i.putExtra("name",studentName)
            startActivity(i);
        }
    }


    fun updateUi(x:BasicDetails){
        progressbar.visibility= INVISIBLE
        studentName= x.sName.toString()
        student_name.text=x.sName
        student_class.text=x.sClass
        student_dob.text=x.sDob
        student_blood.text=x.sBlood
        student_genere.text=x.sSex
        student_father.text=x.sFatherName
        student_mother.text=x.sMotherName
        student_rollno.text=x.sRollNumber

        if(x.isPhotoTaken=="false"){
            if (x.sSex=="Male"){
                student_imageview.setImageResource(R.drawable.male1)
                
            }else{
                student_imageview.setImageResource(R.drawable.female1)

            }
        }
        details_layout.visibility= VISIBLE
    }
}