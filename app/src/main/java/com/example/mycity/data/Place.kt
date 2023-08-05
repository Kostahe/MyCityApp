package com.example.mycity.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place (
    val id: Long,
    @DrawableRes val imageRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val descRes: Int,
    @StringRes val addressRes: Int,
    val placeType: PlaceType
)

