package com.home.lhduy.maboassistant.Room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.home.lhduy.maboassistant.APP_DATABASE

@Database(entities = arrayOf(Home::class), version = 3)

abstract class AppDatabase : RoomDatabase(){


    abstract fun homeDAO(): homeDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, APP_DATABASE
        ).allowMainThreadQueries()
            .build()
    }

}