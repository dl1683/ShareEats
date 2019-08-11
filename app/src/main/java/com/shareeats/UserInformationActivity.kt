package com.shareeats

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserInformationActivity : AppCompatActivity() {

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    //UI elements
    private var tvFirstName: TextView? = null
    private var tvVolunteer: TextView? =null
    private var tvEmail: TextView? = null
    private var tvEmailVerified: TextView? = null
    private var tvPhoneNumber: TextView? = null

    private var btnSignOut: Button?=null
    private var btnMenu: Button?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)
        initialise()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvFirstName = findViewById<View>(R.id.tv_first_name) as TextView
        tvVolunteer = findViewById<View>(R.id.tv_volunteer) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerified = findViewById<View>(R.id.tv_email_verified) as TextView
        tvPhoneNumber=findViewById<View>(R.id.tv_phone) as TextView
        btnSignOut=findViewById<View>(R.id.SignOut) as Button
        btnMenu=findViewById<View>(R.id.menu) as Button

        btnSignOut!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@UserInformationActivity, MainActivity::class.java)
                startActivity(intent);


            }
        })

        btnMenu!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@UserInformationActivity, Menu::class.java)
                startActivity(intent);


            }
        })
    }


    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        tvEmail!!.text = mUser.email
        tvEmailVerified!!.text = mUser.isEmailVerified.toString()
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //tvFirstName!!.text=mUserReference.child("name").toString()
                //tvPhoneNumber!!.text=mUserReference.child("phoneNumber").toString()
                tvFirstName!!.text = snapshot.child("name").value as String
                tvPhoneNumber!!.text = snapshot.child("phoneNumber").value as String
                tvVolunteer?.text=snapshot.child("volunteer").value.toString()
                val message="Details"
                Toast.makeText(this@UserInformationActivity, message, Toast.LENGTH_SHORT).show()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                val message="Unable to retrieve data"
                Toast.makeText(this@UserInformationActivity, ""+databaseError, Toast.LENGTH_SHORT).show()
            }
        })

    }


}
