package com.example.mycity.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.mycity.R
import com.example.mycity.data.Place

@Composable
fun CityList(
    uiState: CityUiState,
    onCardClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        items(uiState.currentPlaceTypePlaces) {place ->
            PlaceItem(
                place = place,
                onCardClick = onCardClick
            )
        }
    }
}

@Composable
fun CityListAndPlaceDetail(
    uiState: CityUiState,
    onCardClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        CityList(
            uiState = uiState,
            onCardClick = onCardClick,
            modifier.weight(2f)
        )
        val activity = (LocalContext.current as Activity)

        PlaceDetail(
            selectedPlace = uiState.currentPlace,
            onBackPressed = { activity.finish() },
            modifier = Modifier.weight(3f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceItem(
    place: Place,
    onCardClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = { onCardClick(place) },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.place_image_size))
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = stringResource(id = place.titleRes),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.place_image_size)),
                contentScale = ContentScale.Crop
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))

            ) {
                Text(
                    text = stringResource(id = place.titleRes),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.place_header_padding_top)),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(id = place.descRes),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 3
                )
            }
        }
    }
}