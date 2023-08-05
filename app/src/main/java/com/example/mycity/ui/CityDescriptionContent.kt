package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycity.R
import com.example.mycity.data.Place

@Composable
fun PlaceDetail(
    selectedPlace: Place,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        Column {
            Text(
                text = stringResource(id = selectedPlace.titleRes),
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(id = selectedPlace.descRes),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large))
            )
            Spacer(modifier = Modifier.height(30.dp))
            Column {
                Row {
                    Row {
                        Icon(imageVector = Icons.Default.Call, contentDescription = null)
                        Text(text = "Operator")
                    }
                }
                Row {

                }
            }
        }
    }

    @Composable
    fun InfoDescription(
        place: Place
    ) {
        Card {
            Column {
                Row {
                    Icon(imageVector = Icons.Default.Place, contentDescription = null)
                    Text(text = stringResource(id = place.addressRes))
                }
                Row {
                    Text(text = stringResource(id = telephoneRes))
                }
                Row {
                    Text(text = stringResource(id = openFromToRes))
                }
            }
        }

    }
}