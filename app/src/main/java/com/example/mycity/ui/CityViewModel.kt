package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType
import com.example.mycity.data.local.LocalPlacesDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentPlaceType(placeType: PlaceType) {
        _uiState.update { currentState ->
            currentState.copy(
                currentPlaceType = placeType,
                currentPlaceTypePlaces = LocalPlacesDataProvider.allPlaces.filter { place: Place ->
                    placeType == place.placeType
                }
            )
        }
        _uiState.update { currentState ->
            currentState.copy(
                currentPlace = uiState.value.currentPlaceTypePlaces[0]
            )
        }
    }

    fun updateCurrentPlace(place: Place) {
        _uiState.update { currentState ->
            currentState.copy(currentPlace = place)
        }
    }

    fun navigateToListPage() {
        _uiState.update { currentState ->
            currentState.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update { currentState ->
            currentState.copy(isShowingListPage = false)
        }
    }
}
