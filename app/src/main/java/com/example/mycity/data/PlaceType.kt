package com.example.mycity.data

import androidx.annotation.StringRes
import com.example.mycity.R

enum class PlaceType(
    @StringRes val titleRes: Int
) {
    ShoppingCentre(titleRes = R.string.shopping_centers_title),
    Parks(titleRes = R.string.parks_title)
}