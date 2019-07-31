package com.shareeats

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.R.id.button3
import android.R.id.button2
import android.R.id.button1



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val intent = Intent(this@MainActivity, HomePage::class.java)
            startActivity(intent);
        } else {
            initialize()
        }

    }
    private fun initialize(){
        //intialize the UI elements
        var btnLogin = findViewById<View>(R.id.btn_log) as Button
        var btnCreateAccount = findViewById<View>(R.id.btn_create) as Button


        btnLogin.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent);

            }
        })
        btnCreateAccount.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, CreateAccountActivity::class.java)
                startActivity(intent);
            }
        })

    }



}
