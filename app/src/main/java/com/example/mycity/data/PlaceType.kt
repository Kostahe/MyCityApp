package com.example.mycity.data

import androidx.annotation.StringRes
import com.example.mycity.R

enum class PlaceType(
    @StringRes val titleRes: Int
) {
    ShoppingCentre(titleRes = R.string.shopping_centers_title),
    Park(titleRes = R.string.parks_title),
    Castle(titleRes = R.string.castle_title)
}