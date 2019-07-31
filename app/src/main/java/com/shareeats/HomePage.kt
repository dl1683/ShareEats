package com.shareeats

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.app_bar_home_page.*

class HomePage : AppCompatActivity() {

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    //UI elements
    private var tvFirstName: TextView? = null
    private var tvLastName: TextView? = null
    private var tvEmail: TextView? = null
    private var tvEmailVerified: TextView? = null
    private var tvPhoneNumber: TextView? = null

    private var btnSignOut: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        initialise()
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("users")
        mAuth = FirebaseAuth.getInstance()
        tvFirstName = findViewById<View>(R.id.tv_first_name) as TextView
        tvLastName = findViewById<View>(R.id.tv_last_name) as TextView
        tvEmail = findViewById<View>(R.id.tv_email) as TextView
        tvEmailVerified = findViewById<View>(R.id.tv_email_verified) as TextView
        tvPhoneNumber=findViewById<View>(R.id.tv_phone) as TextView
        btnSignOut=findViewById<View>(R.id.SignOut) as Button

        btnSignOut!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this@HomePage, MainActivity::class.java)
                startActivity(intent);


            }
        })
    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child("Users").child(mUser!!.uid)
        tvEmail!!.text = mUser.email
        tvEmailVerified!!.text = mUser.isEmailVerified.toString()
        tvFirstName!!.text=mUserReference.child("name").toString()
        tvPhoneNumber!!.text=mUserReference.child("phoneNumber").toString()
        /*mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvFirstName!!.text = snapshot.child("firstName").value as String
                tvLastName!!.text = snapshot.child("lastName").value as String
                tvPhoneNumber!!.text = snapshot.child("phoneNumber").value as String
                val message="Details"
                Toast.makeText(this@HomePage, message, Toast.LENGTH_SHORT).show()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                val message="Unable to retrieve data"
                Toast.makeText(this@HomePage, ""+databaseError, Toast.LENGTH_SHORT).show()
            }
        })*/

    }


}
