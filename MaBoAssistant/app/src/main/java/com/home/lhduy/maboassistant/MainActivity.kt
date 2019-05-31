package com.home.lhduy.maboassistant

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.homeDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dao: homeDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        ivPhone.setOnClickListener {
            val intent = Intent(this,PhoneActivity::class.java)
            startActivity(intent)
        }

        tvHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        tvPhone.setOnClickListener {
            val intent = Intent(this,PhoneActivity::class.java)
            startActivity(intent)
        }

        initAppDatabase()
    }

    private fun initAppDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, APP_DATABASE
        ).allowMainThreadQueries()
            .build()
        dao = db.homeDAO()

    }
}
