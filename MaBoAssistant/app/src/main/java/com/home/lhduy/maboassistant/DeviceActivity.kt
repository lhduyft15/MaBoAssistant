package com.home.lhduy.maboassistant

import android.app.Dialog
import android.app.ProgressDialog
import android.bluetooth.BluetoothClass
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.Home
import com.home.lhduy.maboassistant.Room.homeDAO
import kotlinx.android.synthetic.main.activity_device.*
import kotlinx.android.synthetic.main.device_adapter.*
import kotlin.concurrent.thread


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
        val progressDialog = ProgressDialog(this)
        getData()
        getDataForFireBase(roomName)
        progressDialog.setMessage("Updating status device")
        progressDialog.show()
        Handler().postDelayed({progressDialog.dismiss()},1500)

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

    private fun getDataForFireBase(room : String){

        val livingDevice1 = database.getReference("living device 1")
        val diningDevice1 = database.getReference("dining device 1")
        val bedDevice1 = database.getReference("bed device 1")
        val bathDevice1 = database.getReference("bath device 1")
        val garageDevice1 = database.getReference("garage device 1")

        val livingDevice2 = database.getReference("living device 2")
        val diningDevice2 = database.getReference("dining device 2")
        val bedDevice2 = database.getReference("bed device 2")
        val bathDevice2 = database.getReference("bath device 2")
        val garageDevice2 = database.getReference("garage device 2")


//        ref.child("test").setValue(1).addOnCompleteListener {
//            Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()
//        }
//        ref.child("test1").setValue(1).addOnCompleteListener {
//            Toast.makeText(applicationContext,"Success", Toast.LENGTH_LONG).show()
//        }
        when(room){
            "Living" ->{
                livingDevice1.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){

                            sttLivingDevice1 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle1)
                            changeImgToOnForDevice(1)
                        }
                        else{

                            sttLivingDevice1 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle1)
                            changeImgToOffForDevice(1)
                        }
                    }
                })

                livingDevice2.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){


                            sttLivingDevice2 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle2)
                            changeImgToOnForDevice(2)
                        }
                        else{

                            sttLivingDevice2 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle2)
                            changeImgToOffForDevice(2)
                        }
                    }
                })
            }
            "Dining" ->{
                diningDevice1.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){

                            sttDiningDevice1 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle1)
                            changeImgToOnForDevice(1)
                        }
                        else{

                            sttDiningDevice1 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle1)
                            changeImgToOffForDevice(1)
                        }
                    }
                })

                diningDevice2.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttDiningDevice2 = true
                            println("dining 2 ${p0.value}")
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle2)
                            changeImgToOnForDevice(2)
                        }
                        else{
                            sttDiningDevice2 = false
                            println("dining 2 ${p0.value}")
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle2)
                            changeImgToOffForDevice(2)
                        }
                    }
                })
            }
            "Bed" ->{
                bedDevice1.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttBedDevice1 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle1)
                            changeImgToOnForDevice(1)
                        }
                        else{
                            sttBedDevice1 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle1)
                            changeImgToOffForDevice(1)
                        }
                    }
                })

                bedDevice2.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttBedDevice2 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle2)
                            changeImgToOnForDevice(2)
                        }
                        else{
                            sttBedDevice2 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle2)
                            changeImgToOffForDevice(2)
                        }
                    }
                })
            }

            "Bath" ->{
                bathDevice1.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttBathDevice1 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle1)
                            changeImgToOnForDevice(1)
                        }
                        else{
                            sttBathDevice1 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle1)
                            changeImgToOffForDevice(1)
                        }
                    }
                })

                bathDevice2.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttBathDevice2 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle2)
                            changeImgToOnForDevice(2)
                        }
                        else{
                            sttBathDevice2 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle2)
                            changeImgToOffForDevice(2)
                        }
                    }
                })
            }

            else ->{
                garageDevice1.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttGarageDevice1 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle1)
                            changeImgToOnForDevice(1)
                        }
                        else{
                            sttGarageDevice1 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle1)
                            changeImgToOffForDevice(1)
                        }
                    }
                })

                garageDevice2.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val data = p0.value!!.toString()
                        if(data == "1"){
                            sttGarageDevice2 = true
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_on)
                                .into(ivToggle2)
                            changeImgToOnForDevice(2)
                        }
                        else{
                            sttGarageDevice2 = false
                            Glide.with(this@DeviceActivity)
                                .load(R.drawable.toggle_off)
                                .into(ivToggle2)
                            changeImgToOffForDevice(2)
                        }
                    }
                })
            }
        }
    }


}
