package com.srmstudios.jungsoomarket.data.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "qr_url") val qrUrl: String,
    val thumbnail: String,
    val name: String,
    val price: String
): Parcelable