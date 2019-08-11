package com.shareeats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ItemActivity : AppCompatActivity() {

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
    private fun initialize(){
        tvItems=findViewById<View>(R.id.items) as TextView
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btnHome=findViewById<View>(R.id.btn_home) as Button

    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val snap=snapshot.child("items").value as HashMap<String,Any>
                val iter=snap.keys.toList()
                var stuff=""
                for(key in iter){
                    val name=snapshot.child("items").child(key).child("name").value.toString()
                    val expiry=snapshot.child("items").child(key).child("expiry").value.toString()
                    stuff+="Name: "+name+" Expiry: "+expiry+"\n"
                }
                tvItems?.text=stuff
                val message="Details"
                Toast.makeText(this@ItemActivity,message, Toast.LENGTH_SHORT).show()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ItemActivity, ""+databaseError, Toast.LENGTH_SHORT).show()
            }
        })

        btnHome!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@ItemActivity, Menu::class.java)
                startActivity(intent);

            }
        })


    }

}
