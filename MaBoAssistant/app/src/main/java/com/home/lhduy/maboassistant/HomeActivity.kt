package com.home.lhduy.maboassistant

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.Home
import com.home.lhduy.maboassistant.Room.homeDAO
import kotlinx.android.synthetic.main.activity_device.*
import kotlinx.android.synthetic.main.activity_home.*
import java.sql.Types.NULL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class HomeActivity : AppCompatActivity() {

    var rooms : ArrayList<Home> = ArrayList()
    lateinit var homeAdapter : HomeAdapter
    lateinit var dao : homeDAO
    lateinit var db: AppDatabase
    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = AppDatabase.invoke(this) // get Room database instance
        dao = db.homeDAO()

        setupRecyclerView()

        getRooms()

        addRoom.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddRoomActivity::class.java)
            startActivityForResult(intent, CODE_ADD_ROOM)
        }

    }

    private fun setupRecyclerView() {
        rvRooms.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        homeAdapter = HomeAdapter(rooms, this)

        rvRooms.adapter = homeAdapter

        homeAdapter.setListener(homeItemClickListener)
    }


    private val homeItemClickListener = object : HomeItemClickListener {
        override fun onItemClicked(position: Int) {

               val intent = Intent(this@HomeActivity, DeviceActivity::class.java)

                intent.putExtra("A", rooms[position].roomName)
                intent.putExtra("B",rooms[position].roomImg)
                intent.putExtra("C",rooms[position].device1Name)
                intent.putExtra("D",rooms[position].device2Name)
                intent.putExtra("E",rooms[position].device1Img)
                intent.putExtra("F",rooms[position].device2Img)
                startActivity(intent)

        }

        override fun onItemLongClicked(position: Int) {

            val builder = AlertDialog.Builder(this@HomeActivity)
            builder.setTitle("Confirmation")
                .setMessage("Remove ${rooms[position].roomName} ?")
                .setPositiveButton("OK") { _, _ ->
                    removeDeviceOnFirebase(rooms[position].roomName)
                    removeItem(position)

                }
                .setNegativeButton(
                    "Cancel"
                ) { dialog, _ -> dialog?.dismiss() }

            val myDialog = builder.create();
            myDialog.show()
        }

        override fun onEditIconClicked(position: Int) {

        }
    }

    private fun getRooms() {
        val roomAddeds = dao.getAll()


        if(roomAddeds.isNotEmpty()){
            ll_empty_view.visibility = View.GONE
        }
        else{
            ll_empty_view.visibility = View.VISIBLE
        }

        this.rooms.addAll(roomAddeds)

        homeAdapter.notifyDataSetChanged() // notify data changed
    }

    private fun removeItem(position: Int) {
        dao.delete(rooms[position]) // remove from Room database  //

        rooms.removeAt(position)

        checkHaveAddedRoom()

        homeAdapter.notifyItemRemoved(position) // notify data change
        Timer(false).schedule(500) {
            runOnUiThread {
                homeAdapter.setData(rooms)
                homeAdapter.notifyDataSetChanged()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CODE_ADD_ROOM && resultCode == Activity.RESULT_OK){
            val newRoomAdded = data?.extras?.getParcelable(HOME_DATA) as Home
            handleOnNewRoomAdded(newRoomAdded)
            ll_empty_view.visibility = View.GONE
        }
    }

    private fun handleOnNewRoomAdded(home : Home){
        homeAdapter.appendData(home)
    }

    private fun checkHaveAddedRoom(){
        val roomAddeds = dao.getAll() // get Students from ROOM database


        if(roomAddeds.isNotEmpty()){
            ll_empty_view.visibility = View.GONE
        }
        else{
            ll_empty_view.visibility = View.VISIBLE
        }

    }

    private fun removeDeviceOnFirebase(roomName : String){
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
                livingDevice1.setValue(null)
                livingDevice2.setValue(null)

            }
            "Dining" ->{
                diningDevice1.setValue(null)
                diningDevice2.setValue(null)

            }
            "Bed" ->{
                bedDevice1.setValue(null)
                bedDevice2.setValue(null)
            }

            "Bath" ->{
                bathDevice1.setValue(null)
                bathDevice2.setValue(null)
            }

            else ->{
                garageDevice1.setValue(null)
                garageDevice2.setValue(null)
            }
        }
    }
}
