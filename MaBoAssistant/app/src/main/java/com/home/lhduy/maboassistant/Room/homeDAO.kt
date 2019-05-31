package com.home.lhduy.maboassistant.Room

import android.arch.persistence.room.*

@Dao
interface homeDAO {
    @Query("SELECT * FROM Home")
    fun getAll() : List<Home>

    @Query("SELECT * FROM Home WHERE id=:id")
    fun findById(id : Int) : Home

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Home): Int

    @Delete
    fun delete(todo: Home)

    @Query("SELECT * FROM Home WHERE id=:id")
    fun deleteById(id : Int): Boolean

    @Update
    fun update(home : Home)

}
