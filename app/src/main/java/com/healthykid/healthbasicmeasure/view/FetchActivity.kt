package com.healthykid.healthbasicmeasure.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.FetchActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_fetch.*
import kotlinx.android.synthetic.main.activity_login.*

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
            if(checkInternet()){
                val uhId=student_uhid_ed.text.toString()
                if (uhId.length>11){
                    fetch_progressbar.visibility=VISIBLE
                    viewModel.getStudentDetails(uhId)

                }else{
                    student_uhid_ed.error="Enter valid UhId"
                }
            }else{
                fetch_internet_layout.visibility= VISIBLE
                fetchLayout.visibility= GONE
                refresh()
                //Toast.makeText(this,"Turn On Your Internet Connection",Toast.LENGTH_SHORT).show()
            }

        }

        fetch_refresh_btn.setOnClickListener {
            refresh()
        }
        student_uhid_ed.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val length=p0?.length
                val id=p0.toString()+"-"
                if (length==3 || length==8){
                    student_uhid_ed.setText(id)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }
    private fun checkInternet():Boolean{
        val connectManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=connectManager.activeNetworkInfo
        val isConected=activeNetwork?.isConnectedOrConnecting == true
        return isConected
    }

    private fun refresh(){
        if (checkInternet()){
            fetchLayout.visibility= VISIBLE
            fetch_internet_layout.visibility= INVISIBLE
        }else{
            Toast.makeText(this,"No InternetConnection!",Toast.LENGTH_SHORT).show()
        }
    }
}