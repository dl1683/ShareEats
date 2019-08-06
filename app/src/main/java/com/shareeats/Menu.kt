package com.shareeats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Menu : AppCompatActivity() {
    private var btnItems: Button? =null
    private var btnShare: Button? =null
    private var btnAdd: Button? =null
    private var btnInfo: Button? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initialize()
    }
    private fun initialize(){
        btnShare = findViewById<View>(R.id.share) as Button
        btnItems = findViewById<View>(R.id.items) as Button
        btnAdd = findViewById<View>(R.id.add) as Button
        btnInfo = findViewById<View>(R.id.info) as Button

        btnItems!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@Menu, ItemActivity::class.java) //change to other
                startActivity(intent);
            }
        })

        btnShare!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@Menu, UserInformationActivity::class.java) //change to other
                startActivity(intent);
            }
        })

        btnAdd!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@Menu, AddItemActivity::class.java) //change to other
                startActivity(intent);
            }
        })

        btnInfo!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@Menu, UserInformationActivity::class.java) //change to other
                startActivity(intent);
            }
        })
    }
}
