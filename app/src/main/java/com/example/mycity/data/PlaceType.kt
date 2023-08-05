package com.example.mycity.data

import androidx.annotation.StringRes
import com.example.mycity.R

enum class PlaceType(
    @StringRes val titleRes: Int
) {
    ShoppingMalls(titleRes = R.string.shopping_malls_title),
    Parks(titleRes = R.string.parks_title)
}