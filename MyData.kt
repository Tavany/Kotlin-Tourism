package com.eja.tugasbesar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyData(
    var name: String,
    var description: String,
    var photo: Int,
    val lat: Double,
    val lang: Double
) : Parcelable

