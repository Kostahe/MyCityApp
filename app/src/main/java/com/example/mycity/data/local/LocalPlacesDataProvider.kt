package com.example.mycity.data.local

import com.example.mycity.R
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType

object LocalPlacesDataProvider {
    val allPlaces = listOf(
        Place(
            id = 0L,
            imageRes = R.drawable.japonska_zahrada,
            titleRes = R.string.japanese_garden_title,
            descRes = R.string.japanese_garden_desc,
            addressRes = R.string.japanese_garden_address,
            placeType = PlaceType.Park,
            telephoneRes = R.string.japanese_garden_telephone_number,
            openFromToRes = R.string.japanese_garden_open_from_to,
            price = null
        ),
        Place(
            id = 1L,
            imageRes = R.drawable.obchodni_centrum_palladium,
            titleRes = R.string.shopping_centre_palladium_title,
            descRes = R.string.shopping_centre_palladium_desc,
            addressRes = R.string.shopping_centre_palladium_address,
            placeType = PlaceType.ShoppingCentre,
            telephoneRes = R.string.shopping_centre_palladium_telephone_number,
            openFromToRes = R.string.shopping_centre_cerny_most_open_from_to,
            price = null
        ),
        Place(
            id = 2L,
            imageRes = R.drawable.obchodni_centum_cerny_most,
            titleRes = R.string.shopping_centre_cerny_most_title,
            descRes = R.string.shopping_centre_cerny_most_desc,
            addressRes = R.string.shopping_centre_cerny_most_address,
            placeType = PlaceType.ShoppingCentre,
            telephoneRes = R.string.shopping_centre_cerny_most_telephone_number,
            openFromToRes = R.string.shopping_centre_cerny_most_open_from_to,
            price = null
        ),
        Place(
            id = 3L,
            imageRes = R.drawable.zamek_troja,
            titleRes = R.string.castle_troja_title,
            descRes = R.string.castle_troja_desc,
            addressRes = R.string.castle_troja_address,
            placeType = PlaceType.Castle,
            telephoneRes = R.string.castle_troja_telephone_number,
            openFromToRes = R.string.castle_troja_open_from_to,
            price = R.string.castle_troja_price
        ),

    )
}