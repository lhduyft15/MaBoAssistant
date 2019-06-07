package com.home.lhduy.maboassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.home.lhduy.maboassistant.Room.Home
import kotlinx.android.synthetic.main.activity_device.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.device_adapter.*


class DeviceActivity : AppCompatActivity() {
    var roomName = ""
    var device1Name = ""
    var device2Name = ""
    val database = FirebaseDatabase.getInstance()
    var sttLivingDevice1 = false
    var sttLivingDevice2 = false
    var sttDiningDevice1 = false
    var sttDiningDevice2 = false
    var sttBedDevice1 = false
    var sttBedDevice2 = false
    var sttBathDevice1 = false
    var sttBathDevice2 = false
    var sttGarageDevice1 = false
    var sttGarageDevice2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        getData()

        when(roomName){
            "Living" -> {
                ivToggle1.setOnClickListener {
                    val livingDevice1 = database.getReference("living device 1")
                    if(sttLivingDevice1){
                        livingDevice1.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle1)
                        sttLivingDevice1 = false

                        changeImgToOffForDevice(1)

                    }
                    else{
                        livingDevice1.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle1)
                        sttLivingDevice1 = true

                        changeImgToOnForDevice(1)

                    }
                }
                ivToggle2.setOnClickListener {
                    val livingDevice2 = database.getReference("living device 2")
                    if(sttLivingDevice2){
                        livingDevice2.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle2)
                        sttLivingDevice2 = false

                        changeImgToOffForDevice(2)
                    }
                    else{
                        livingDevice2.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle2)
                        sttLivingDevice2 = true

                        changeImgToOnForDevice(2)
                    }
                }

            }
            "Dining" -> {
                ivToggle1.setOnClickListener {
                    val diningDevice1 = database.getReference("dining device 1")
                    if(sttDiningDevice1){
                       diningDevice1.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle1)
                        sttDiningDevice1 = false

                        changeImgToOffForDevice(1)

                    }
                    else{
                        diningDevice1.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle1)
                        sttDiningDevice1 = true

                        changeImgToOnForDevice(1)

                    }
                }
                ivToggle2.setOnClickListener {
                    val diningDevice2 = database.getReference("dining device 2")
                    if(sttDiningDevice2){
                        diningDevice2.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle2)
                        sttDiningDevice2 = false

                        changeImgToOffForDevice(2)
                    }
                    else{
                        diningDevice2.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle2)
                        sttDiningDevice2 = true

                        changeImgToOnForDevice(2)
                    }
                }

            }
            "Bed" ->  {
                ivToggle1.setOnClickListener {
                    val bedDevice1 = database.getReference("bed device 1")
                    if(sttBedDevice1){
                        bedDevice1.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle1)
                        sttBedDevice1 = false

                        changeImgToOffForDevice(1)

                    }
                    else{
                        bedDevice1.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle1)
                        sttBedDevice1 = true

                        changeImgToOnForDevice(1)

                    }
                }
                ivToggle2.setOnClickListener {
                    val bedDevice2 = database.getReference("bed device 2")
                    if(sttBedDevice2){
                       bedDevice2.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle2)
                        sttBedDevice2 = false

                        changeImgToOffForDevice(2)
                    }
                    else{
                       bedDevice2.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle2)
                        sttBedDevice2 = true

                        changeImgToOnForDevice(2)
                    }
                }

            }
            "Bath"-> {
                ivToggle1.setOnClickListener {
                    val bathDevice1 = database.getReference("bath device 1")
                    if(sttBathDevice1){
                        bathDevice1.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle1)
                        sttBathDevice1 = false

                        changeImgToOffForDevice(1)

                    }
                    else{
                       bathDevice1.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle1)
                        sttBathDevice1 = true

                        changeImgToOnForDevice(1)

                    }
                }
                ivToggle2.setOnClickListener {
                    val bathDevice2 = database.getReference("bath device 2")
                    if(sttBathDevice2){
                    bathDevice2.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle2)
                        sttBathDevice2 = false

                        changeImgToOffForDevice(2)
                    }
                    else{
                        bathDevice2.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle2)
                        sttBathDevice2 = true

                        changeImgToOnForDevice(2)
                    }
                }
            }
            "Garage" -> {
                ivToggle1.setOnClickListener {
                    val garageDevice1 = database.getReference("garage device 1")
                    if(sttGarageDevice1){
                       garageDevice1.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle1)
                        sttGarageDevice1 = false

                        changeImgToOffForDevice(1)

                    }
                    else{
                        garageDevice1.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle1)
                        sttGarageDevice1 = true

                        changeImgToOnForDevice(1)

                    }
                }
                ivToggle2.setOnClickListener {
                    val garageDevice2 = database.getReference("garage device 2")
                    if(sttGarageDevice2){
                       garageDevice2.setValue(0)
                        Glide.with(this)
                            .load(R.drawable.toggle_off)
                            .into(ivToggle2)
                        sttGarageDevice2 = false

                        changeImgToOffForDevice(2)
                    }
                    else{
                       garageDevice2.setValue(1)
                        Glide.with(this)
                            .load(R.drawable.toggle_on)
                            .into(ivToggle2)
                        sttGarageDevice2 = true

                        changeImgToOnForDevice(2)
                    }
                }
            }
        }
    }

    private fun getData(){
        val data = intent.extras
        Log.e("EEEEE","eeee")
        roomName = data.getString("A")
        var roomImg = data.getInt("B")
         device1Name = data.getString("C")
         device2Name = data.getString("D")
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

    private fun changeImgToOnForDevice(index : Int){
        if( index == 1){
            when(device1Name){
                "Led" ->{
                    Glide.with(this)
                        .load(R.drawable.lamp_on)
                        .into(ivDeviceImgItem1)
                }
                "Fan" ->{
                    Glide.with(this)
                        .load(R.drawable.fan_on)
                        .into(ivDeviceImgItem1)
                }
                "TV" ->{
                    Glide.with(this)
                        .load(R.drawable.tv_on)
                        .into(ivDeviceImgItem1)
                }
                else ->{
                    Glide.with(this)
                        .load(R.drawable.socket_on)
                        .into(ivDeviceImgItem1)
                }
            }
        }
        else{
            when(device2Name){
                "Led" ->{
                    Glide.with(this)
                        .load(R.drawable.lamp_on)
                        .into(ivDeviceImgItem2)
                }
                "Fan" ->{
                    Glide.with(this)
                        .load(R.drawable.fan_on)
                        .into(ivDeviceImgItem2)
                }
                "TV" ->{
                    Glide.with(this)
                        .load(R.drawable.tv_on)
                        .into(ivDeviceImgItem2)
                }
                else ->{
                    Glide.with(this)
                        .load(R.drawable.socket_on)
                        .into(ivDeviceImgItem2)
                }
            }
        }

    }

    private fun changeImgToOffForDevice(index : Int){

        if(index == 1){
            when(device1Name){
                "Led" ->{
                    Glide.with(this)
                        .load(R.drawable.lamp_disabled)
                        .into(ivDeviceImgItem1)
                }
                "Fan" ->{
                    Glide.with(this)
                        .load(R.drawable.fan_off)
                        .into(ivDeviceImgItem1)
                }
                "TV" ->{
                    Glide.with(this)
                        .load(R.drawable.tv_off)
                        .into(ivDeviceImgItem1)
                }
                else ->{
                    Glide.with(this)
                        .load(R.drawable.socket_off)
                        .into(ivDeviceImgItem1)
                }
            }
        }
        else{
            when(device2Name){
                "Led" ->{
                    Glide.with(this)
                        .load(R.drawable.lamp_disabled)
                        .into(ivDeviceImgItem2)
                }
                "Fan" ->{
                    Glide.with(this)
                        .load(R.drawable.fan_off)
                        .into(ivDeviceImgItem2)
                }
                "TV" ->{
                    Glide.with(this)
                        .load(R.drawable.tv_off)
                        .into(ivDeviceImgItem2)
                }
                else ->{
                    Glide.with(this)
                        .load(R.drawable.socket_off)
                        .into(ivDeviceImgItem2)
                }
            }
        }

    }
}
