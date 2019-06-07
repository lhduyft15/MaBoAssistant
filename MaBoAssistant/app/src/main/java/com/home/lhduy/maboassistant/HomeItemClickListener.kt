package com.home.lhduy.maboassistant



interface HomeItemClickListener {
    fun onItemClicked(position: Int)
    fun onItemLongClicked(position: Int)
    fun onEditIconClicked(position: Int)
}