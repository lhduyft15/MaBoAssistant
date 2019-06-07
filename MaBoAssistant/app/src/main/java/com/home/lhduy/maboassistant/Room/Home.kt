package com.home.lhduy.maboassistant.Room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Home (
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var roomName: String,
    var device1Name: String,
    var device2Name: String,
    var countDevice: Int,
    var roomImg : Int,
    var device1Img : Int,
    var device2Img : Int

) : Parcelable {
    constructor() : this(null,"","","",0,-1,-1,-1)
}
