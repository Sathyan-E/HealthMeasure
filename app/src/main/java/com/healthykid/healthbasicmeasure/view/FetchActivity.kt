package com.healthykid.healthbasicmeasure.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.FetchActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_fetch.*
import kotlinx.android.synthetic.main.activity_login.*

class FetchActivity : AppCompatActivity() {
    private lateinit var viewModel:FetchActivityViewModel
    private lateinit var uhidEditText:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        uhidEditText=findViewById(R.id.student_uhid_ed)

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
                fetch_error_tv.text="Invalid UHID"
                fetch_error_tv.visibility= VISIBLE
            }else{
                fetch_error_tv.visibility= INVISIBLE
            }

        })


        fetch_details_btn.setOnClickListener {
            hideKeyboard()

            if(checkInternet()){
                val uhId=uhidEditText.text.toString()
                if (uhId.length>11){
                    fetch_progressbar.visibility=VISIBLE
                    viewModel.getStudentDetails(uhId)

                }else{
                    fetch_error_tv.text="Invalid UHID"
                    fetch_error_tv.visibility= VISIBLE
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
        uhidEditText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                fetch_error_tv.visibility= INVISIBLE
                val length=p0?.length
                val id=p0.toString()+"-"
              /**
                uhidEditText.setOnKeyListener ( object:OnKeyListener{
                    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                     //   if (p1 == p2.KEYCODE_DEL )
                    }

                } )
                **/
                if (length==3){
                    uhidEditText.setText(id)
                    uhidEditText.setSelection(4)
                }
                else if (length==8){
                    uhidEditText.setText(id)
                    uhidEditText.setSelection(9)
                }
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

    override fun onResume() {
        super.onResume()
        fetch_progressbar.visibility= INVISIBLE
    }
    private fun hideKeyboard(){
        val view=this.currentFocus
        view?.let {
            val v=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            v?.hideSoftInputFromWindow(it.windowToken,0)
        }
    }
}