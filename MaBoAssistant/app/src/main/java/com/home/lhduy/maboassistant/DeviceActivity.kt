package com.home.lhduy.maboassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.home.lhduy.maboassistant.Room.Home
import kotlinx.android.synthetic.main.activity_device.*

class DeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        val data = intent.extras
        Log.e("EEEEE","eeee")
        var roomName = data.getString("A")
        var roomImg = data.getInt("B")
        var device1Name = data.getString("C")
        var device2Name = data.getString("D")
        var device1Img = data.getInt("E")
        var device2Img = data.getInt("F")
        Glide.with(this)
            .load(roomImg)
            .centerCrop()
            .into(ivRoom)
        if(device1Name == "---Select Device---"){
            ll_device1.visibility = View.GONE
        }
        else{
            Glide.with(this)
                .load(device1Img)
                .into(ivDeviceImgItem1)
        }
        if(device2Name == "---Select Device---"){
            ll_device2.visibility = View.GONE
        }
        else{
            Glide.with(this)
                .load(device2Img)
                .into(ivDeviceImgItem2)
        }

    }
}
