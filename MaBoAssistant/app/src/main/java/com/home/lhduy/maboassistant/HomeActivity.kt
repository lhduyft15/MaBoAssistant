package com.home.lhduy.maboassistant

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.home.lhduy.maboassistant.Room.AppDatabase
import com.home.lhduy.maboassistant.Room.Home
import com.home.lhduy.maboassistant.Room.homeDAO
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

//            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
//            intent.putExtra(STUDENT_NAME_KEY, students[position].name)
//            intent.putExtra(STUDENT_AVATAR_KEY, students[position].avatarOfTeacher)
//            intent.putExtra(STUDENT_CLUB_KEY, students[position].classz)
//            startActivity(intent)

        }

        override fun onItemLongClicked(position: Int) {

            val builder = AlertDialog.Builder(this@HomeActivity)
            builder.setTitle("Confirmation")
                .setMessage("Remove ${rooms[position].roomName} ?")
                .setPositiveButton("OK") { _, _ ->
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
        val roomAddeds = dao.getAll() // get Students from ROOM database
        Log.e("RRRR", roomAddeds.toString())

        this.rooms.addAll(roomAddeds) // add to student list

        homeAdapter.notifyDataSetChanged() // notify data changed
    }

    private fun removeItem(position: Int) {
        dao.delete(rooms[position]) // remove from Room database  //

        rooms.removeAt(position) // remove student list on RAM

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
        }
    }

    private fun handleOnNewRoomAdded(home : Home){
        homeAdapter.appendData(home)
    }
}
