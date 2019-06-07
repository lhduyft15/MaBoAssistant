package com.home.lhduy.maboassistant

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.home.lhduy.maboassistant.Room.Home
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.room_adapter.view.*


class HomeAdapter(var items : ArrayList<Home>, val context : Context): RecyclerView.Adapter<HomeViewHolder>() {

    lateinit var mListener : HomeItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.room_adapter, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(homeViewHolder : HomeViewHolder, position : Int) {
        homeViewHolder.tvRoomNameItem.text = "${items[position].roomName}"
        homeViewHolder.tvCountDeviceItem.text = "${items[position].countDevice}"

        if(items[position].roomName != ""){
            //homeViewHolder.ll_empty_view.visibility = View.GONE
        }

        Log.e("RRRR","aaaaaa")
        Glide.with(context)
            .load(items[position].roomImg)
            .into(homeViewHolder.ivRoomImgItem)

        homeViewHolder.itemView.setOnClickListener {
            mListener.onItemClicked(position)
        }

        homeViewHolder.itemView.setOnClickListener {
            mListener.onItemLongClicked(position)
        }

    }

    fun setListener(listener : HomeItemClickListener){
        this.mListener = listener
    }
    fun setData(items: ArrayList<Home>) {
        this.items = items
    }

    fun appendData(newRoomAdded: Home) {
        this.items.add(newRoomAdded)
        notifyItemInserted(items.size - 1)
    }

}

class HomeViewHolder(view : View) : RecyclerView.ViewHolder(view){
    var ivRoomImgItem = view.ivRoomImgItem
    var tvRoomNameItem = view.tvRoomNameItem
    var tvCountDeviceItem = view.tvCountDeviceItem
    var ll_empty_view = view.ll_empty_view

}