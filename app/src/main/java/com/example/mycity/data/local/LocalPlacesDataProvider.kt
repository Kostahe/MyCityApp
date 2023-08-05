package com.example.mycity.data.local

import com.example.mycity.R
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType

object LocalPlacesDataProvider {
    val allPlaces = listOf(
        Place(
           id = 0L,
           imageRes = R.drawable.japonska_zahrada,
           titleRes = R.string.japonska_zahrada_title,
           descRes = R.string.japonska_zahrade_desc,
           addressRes = R.string.japonska_zahrada_address,
           placeType = PlaceType.Parks
        ),
        Place(
            id = 1L,
            imageRes = R.drawable.obchodni_centrum_palladium,
            titleRes = R.string.obchodni_centrum_palladium_title,
            descRes = R.string.obchodni_centrum_palladium_desc,
            addressRes = R.string.obchodni_centrum_palladium_address,
            placeType = PlaceType.ShoppingMalls
        ),
        Place(
            id = 2L,
            imageRes = R.drawable.obchodni_centum_cerny_most,
            titleRes = R.string.obchodni_centrum_cerny_most_title,
            descRes = R.string.obchodni_centrum_cerny_most_desc,
            addressRes = R.string.obchodni_centum_cerny_most_address,
            placeType = PlaceType.ShoppingMalls
        )
    )
}