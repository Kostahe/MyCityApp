package com.example.mycity.ui

import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType
import com.example.mycity.data.local.LocalPlacesDataProvider

data class CityUiState (
    val currentPlaceType: PlaceType = PlaceType.Parks,
    val currentPlaceTypePlaces: List<Place> = LocalPlacesDataProvider.allPlaces.filter { place: Place ->
        place.placeType == currentPlaceType
    },
    val currentPlace: Place = currentPlaceTypePlaces[0],
    val isShowingListPage: Boolean = true
)

