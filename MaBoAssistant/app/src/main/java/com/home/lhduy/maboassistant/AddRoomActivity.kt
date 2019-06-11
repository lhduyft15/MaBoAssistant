package com.home.lhduy.maboassistant

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.Home
import com.home.lhduy.maboassistant.Room.homeDAO
import kotlinx.android.synthetic.main.activity_add_room.*

class AddRoomActivity : AppCompatActivity() {

    val spinnerData = ArrayList<Pair<String,Int>>()
    val spinnerDevice1 = ArrayList<Pair<String,Int>>()
    val spinnerDevice2 = ArrayList<Pair<String,Int>>()
    val database = FirebaseDatabase.getInstance()

    lateinit var db: AppDatabase
    lateinit var dao : homeDAO
    var rooms : ArrayList<Home> = ArrayList()
    var resultCheckRoomExist = false

    var home = Home()
    var roomImg = -1
    var device1Img = -1
    var device2Img = -1
    var roomName = ""
    var device1Name = ""
    var device2Name = ""
    var checkSelectedDevice1 = 0
    var checkSelectedDevice2 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)

        db = AppDatabase.invoke(this)

        setupSpinnerRoom()
        setupSpinnerDevice1()
        setupSpinnerDevice2()
        handleSubmitData()
    }

    private fun handleSubmitData(){
        btnSubmit.setOnClickListener {

            home.roomName = roomName
            home.countDevice = checkSelectedDevice1 + checkSelectedDevice2
            addDeviceOnFirebase(roomName)

            if(home.roomName == "--Select Room--"){

                val builder = AlertDialog.Builder(this@AddRoomActivity)
                builder.setTitle("Warn")
                    .setMessage("Please select room !")
                    .setNegativeButton(
                        "Cancel"
                    ) { dialog, _ -> dialog?.dismiss() }

                val myDialog = builder.create();
                myDialog.show()

            }
            else{
                checkRoomAdded(home.roomName)
                if(resultCheckRoomExist){
                    val builder = AlertDialog.Builder(this@AddRoomActivity)
                    builder.setTitle("Warn")
                        .setMessage("Room existed!")
                        .setNegativeButton(
                            "Cancel"
                        ) { dialog, _ -> dialog?.dismiss() }

                    val myDialog = builder.create();
                    myDialog.show()
                }
                else{
                    if(home.countDevice == 0){

                        val builder = AlertDialog.Builder(this@AddRoomActivity)
                        builder.setTitle("Warn")
                            .setMessage("Please select device !")
                            .setNegativeButton(
                                "Cancel"
                            ) { dialog, _ -> dialog?.dismiss() }

                        val myDialog = builder.create();
                        myDialog.show()
                    }
                    else{

                        home.device1Name = device1Name
                        home.device2Name = device2Name

                        home.roomImg = roomImg
                        home.device1Img = device1Img
                        home.device2Img = device2Img


                        val homeDAO = db.homeDAO()
                        val id = homeDAO.insert(home)

                        home.id = id.toInt()

                        val intent = Intent()
                        intent.putExtra(HOME_DATA, home)
                        setResult(Activity.RESULT_OK,intent)
                        finish()
                    }
                }
            }
        }
    }

    private fun setupSpinnerRoom(){
        spinnerData.add(Pair("--Select Room--",R.drawable.home))
        spinnerData.add(Pair("Living",R.drawable.living))
        spinnerData.add(Pair("Dining",R.drawable.dining))
        spinnerData.add(Pair("Bed",R.drawable.bed))
        spinnerData.add(Pair("Bath",R.drawable.bath))
        spinnerData.add(Pair("Garage",R.drawable.garage))

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData.map { it.first })
        spRoom.adapter = spinnerAdapter
        spRoom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                roomName = spinnerData[position].first
                roomImg = spinnerData[position].second
                Glide.with(this@AddRoomActivity)
                    .load(spinnerData[position].second)
                    .into(ivRoomBackground)

            }
        }
    }

    private fun setupSpinnerDevice1(){
        spinnerDevice1.add(Pair("---Select Device---",R.drawable.question))
        spinnerDevice1.add(Pair("Led",R.drawable.lamp_disabled))
        spinnerDevice1.add(Pair("Fan",R.drawable.fan_off))
        spinnerDevice1.add(Pair("TV",R.drawable.tv_off))
        spinnerDevice1.add(Pair("Socket",R.drawable.socket_off))

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerDevice1.map { it.first })
        spDevice1.adapter = spinnerAdapter
        spDevice1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(spinnerDevice1[position].first != "---Select Device---"){
                    checkSelectedDevice1 = 1
                }

                device1Name = spinnerDevice1[position].first
                device1Img = spinnerDevice1[position].second
                Glide.with(this@AddRoomActivity)
                    .load(spinnerDevice1[position].second)
                    .into(ivDevice1)

            }
        }
    }

    private fun setupSpinnerDevice2(){
        spinnerDevice2.add(Pair("---Select Device---",R.drawable.question))
        spinnerDevice2.add(Pair("Led",R.drawable.lamp_disabled))
        spinnerDevice2.add(Pair("Fan",R.drawable.fan_off))
        spinnerDevice2.add(Pair("TV",R.drawable.tv_off))
        spinnerDevice2.add(Pair("Socket",R.drawable.socket_off))

        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerDevice2.map { it.first })
        spDevice2.adapter = spinnerAdapter
        spDevice2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if(spinnerDevice2[position].first != "---Select Device---"){
                    checkSelectedDevice2 = 1
                }

                device2Name = spinnerDevice2[position].first
                device2Img = spinnerDevice2[position].second
                Glide.with(this@AddRoomActivity)
                    .load(spinnerDevice2[position].second)
                    .into(ivDevice2)

            }
        }
    }

    private fun checkRoomAdded(roomNew : String){
        db = AppDatabase.invoke(this) // get Room database instance
        dao = db.homeDAO()
        val roomAddeds = dao.getAll()
        println("$roomNew duoc check")
        rooms.addAll(roomAddeds)
        resultCheckRoomExist = false
        for(each in rooms){
            println(" check ${each.roomName}")
            if(each.roomName == roomNew){
                resultCheckRoomExist = true
            }
        }
        println("$resultCheckRoomExist ket qua")
    }

    private fun addDeviceOnFirebase(roomName : String){
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

        when(roomName){
            "Living" ->{
                livingDevice1.setValue(0)
                livingDevice2.setValue(0)

            }
            "Dining" ->{
                diningDevice1.setValue(0)
                diningDevice2.setValue(0)

            }
            "Bed" ->{
                bedDevice1.setValue(0)
                bedDevice2.setValue(0)
            }

            "Bath" ->{
                bathDevice1.setValue(0)
                bathDevice2.setValue(0)
            }

            else ->{
                garageDevice1.setValue(0)
                garageDevice2.setValue(0)
            }
        }
    }
}
