package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
                InfoDescription(place = selectedPlace)
            }
        }
    }
}

@Composable
fun InfoDescription(
    place: Place,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Row {
                Icon(imageVector = Icons.Default.Place, contentDescription = null)
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                Text(text = stringResource(id = place.addressRes))
            }
            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row {
                Icon(imageVector = Icons.Default.Call, contentDescription = null)
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                Text(text = stringResource(id = place.telephoneRes))
            }
            Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.schedule_icon), contentDescription = null)
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                Text(text = stringResource(id = place.openFromToRes))
            }
            if(place.price != null) {
                Spacer(modifier = modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Row {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.info_icon), contentDescription = null)
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
                    Text(text = stringResource(id = place.price))
                }
            }
        }
    }

}