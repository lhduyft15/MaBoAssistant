package com.home.lhduy.maboassistant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addRoom.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddRoomActivity::class.java)
            startActivity(intent)
        }
    }
}
