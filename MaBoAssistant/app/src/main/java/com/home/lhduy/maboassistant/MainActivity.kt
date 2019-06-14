package com.home.lhduy.maboassistant

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.homeDAO
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

private const val REQUEST_RECORD_CODE = 12

class MainActivity : AppCompatActivity() {

    lateinit var dao: homeDAO

    private val TAG = "PERMISSION"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPermission()
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

    private fun setPermission(){
        val permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)
        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    //Show popup require audio if permisson hasn't been granted
    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO),
            REQUEST_RECORD_CODE)
    }


    //Funtion is written to debug permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_RECORD_CODE -> {
                if(grantResults.isEmpty()|| grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "Permission has been denied by user")
                }
                else{
                    Log.d(TAG, "Permission has been granted by user")
                }
            }
        }
    }

}
