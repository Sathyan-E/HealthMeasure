package com.healthykid.healthbasicmeasure.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.healthykid.healthbasicmeasure.R
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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)


        val ref: DatabaseReference = FirebaseDatabase.getInstance().reference


        fetch_details_btn.setOnClickListener {
            val uhId=student_uhid_ed.text.toString()
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


            val intent=Intent(this,DetailsActivity::class.java)
            intent.putExtra("id",uhId)
           /**
            intent.putExtra("name",sName)
            intent.putExtra("class",sClass)
            intent.putExtra("dob",sdob)
            intent.putExtra("bg",sbg)
            intent.putExtra("fathername",sFatherName)
            intent.putExtra("mothername",sMotherName)
            intent.putExtra("rollno",sRn)
            intent.putExtra("sex",sSex)**/
            startActivity(intent)
        }
    }
}