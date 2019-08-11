package com.shareeats

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {

    //database
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var etName: EditText? = null
    private var tvExpiry: TextView? = null
    private var btnAddItem: Button? = null
    private var mProgressBar: ProgressDialog? = null
    //global variables
    private var firstName: String? = null
    private var expiry: String? = null
    private var cal= Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        initialise()
    }

    private fun initialise() {
        // intialize all the UI elements
        etName = findViewById<View>(R.id.et_name) as EditText
        tvExpiry = findViewById<View>(R.id.tv_expiry) as TextView
        btnAddItem = findViewById<View>(R.id.btn_add) as Button
        mProgressBar = ProgressDialog(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        tvExpiry!!.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            tvExpiry!!.text = sdf.format(cal.time)

        }

        tvExpiry!!.setOnClickListener {
            DatePickerDialog(this@AddItemActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        btnAddItem!!.setOnClickListener { addItem() }

    }



    private fun addItem() {
        /*create a new account given all the details from the UI elements*/

        //get strings from the elements
        firstName = etName?.text.toString()
        expiry = tvExpiry?.text as String
        //validate the strings and throw error if empty
        if (!TextUtils.isEmpty(firstName)
            && !TextUtils.isEmpty(expiry.toString()) ) {

            //show the progress bar
            mProgressBar!!.setMessage("Adding item")
            mProgressBar!!.show()
            val userId = mAuth!!.currentUser!!.uid


            val item = User.Item(firstName,expiry,userId,null,null)
            val newData=mDatabaseReference!!.child(userId).child("items").push()
            newData.setValue(item)
            updateUserInfoAndUI()

            //create the account

        } else {
            //throw the error
            Toast.makeText(this, "Enter all details. Leave nothing empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserInfoAndUI() {
        //update the UserUI and register them
        //start next activity
        val intent = Intent(this@AddItemActivity, Menu::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }


}
