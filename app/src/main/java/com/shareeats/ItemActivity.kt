package com.shareeats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ItemActivity : AppCompatActivity() {

    private var tvHeading: TextView? =null
    private var tvItems: TextView? =null
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    private var btnHome: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        initialize()
    }
    fun initialize(){
        tvHeading=findViewById<View>(R.id.heading) as TextView
        tvItems=findViewById<View>(R.id.items) as TextView
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvHeading=findViewById<View>(R.id.heading) as TextView
        btnHome=findViewById<View>(R.id.btn_home) as Button


    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //tvFirstName!!.text=mUserReference.child("name").toString()
                //tvPhoneNumber!!.text=mUserReference.child("phoneNumber").toString()
                tvItems?.text=snapshot.child("items").value.toString()
                val message="Details"
                Toast.makeText(this@ItemActivity, message, Toast.LENGTH_SHORT).show()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ItemActivity, ""+databaseError, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
