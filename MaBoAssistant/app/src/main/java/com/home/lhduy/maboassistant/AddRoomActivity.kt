package com.home.lhduy.maboassistant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_add_room.*

class AddRoomActivity : AppCompatActivity() {

    val spinnerData = ArrayList<Pair<String, Int>>()
    val spinnerDevice1 = ArrayList<Pair<String,Int>>()
    val spinnerDevice2 = ArrayList<Pair<String,Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)

        setupSpinnerRoom()
        setupSpinnerDevice1()
        setupSpinnerDevice2()
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
                Glide.with(this@AddRoomActivity)
                    .load(spinnerDevice2[position].second)
                    .into(ivDevice2)

            }
        }
    }
}
