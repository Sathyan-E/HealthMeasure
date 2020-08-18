package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*
import com.healthykid.healthbasicmeasure.R
import com.healthykid.healthbasicmeasure.viewmodel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_fetch.*

class FetchActivity : AppCompatActivity() {
    private var sName: String=""
    private  var sClass: String=""
    private  var sdob: String=""
    private  var sbg: String=""
    private  var sFatherName: String=""
    private  var sMotherName: String=""
    private  var sRn: String=""
    private  var sSex: String=""
    private  var isphoto: String=""
    private lateinit var viewModel:DetailsActivityViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)


        viewModel=ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        viewModel.studentDetails.observe(this, Observer {
            val intent=Intent(this,DetailsActivity::class.java)
            intent.putExtra("basicdetails",it)
            startActivity(intent)

        })


        fetch_details_btn.setOnClickListener {
            val uhId=student_uhid_ed.text.toString()
            if (uhId.length>11){
                viewModel.getStudentDetails(uhId)

            }else{
                student_uhid_ed.error="Enter valid UhId"
            }
          /**
           ref.child("Students").child(uhId).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    //  TODO("Not yet implemented")
                    Toast.makeText(this@FetchActivity,"Oncancelled method is called", Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        sName = snapshot.child("Name").value as String
                        sClass=snapshot.child("Class").value.toString()
                        sdob=snapshot.child("DoB").value.toString()
                        sbg=snapshot.child("Blood_Group").value.toString()
                        sFatherName=snapshot.child("Father_Name").value.toString()
                        sMotherName=snapshot.child("Mother_Name").value.toString()
                        sRn=snapshot.child("Roll_No").value.toString()
                        sSex=snapshot.child("Sex").value.toString()
                        isphoto=snapshot.child("Student_Photo_Taken").value.toString()

                        //Toast.makeText(this@FetchActivity, user, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@FetchActivity,"success but null", Toast.LENGTH_SHORT).show()
                    }

                }

            })
        **/



           /**
            intent.putExtra("name",sName)
            intent.putExtra("class",sClass)
            intent.putExtra("dob",sdob)
            intent.putExtra("bg",sbg)
            intent.putExtra("fathername",sFatherName)
            intent.putExtra("mothername",sMotherName)
            intent.putExtra("rollno",sRn)
            intent.putExtra("sex",sSex)**/

        }
    }
}