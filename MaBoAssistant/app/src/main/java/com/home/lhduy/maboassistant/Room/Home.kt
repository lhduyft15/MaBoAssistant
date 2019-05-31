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
    var deviceName: String,
    var countDevice: Int = 0

) : Parcelable {
    constructor() : this(null,"","",0)
}
