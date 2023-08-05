package com.example.mycity.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType
import com.example.mycity.ui.utils.CityNavigationType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    onBackPressed: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    currentTab: PlaceType,
    currentPlace: Place,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text =
                if (!isShowingDetailPage) {
                    stringResource(id = currentTab.titleRes)
                } else {
                    stringResource(id = currentPlace.titleRes)
                },
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (isShowingDetailPage) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: CityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentTab = PlaceType.valueOf(
        backStackEntry?.destination?.route ?: PlaceType.Parks.name
    )

    val navigationType: CityNavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            CityNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            CityNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            CityNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            CityNavigationType.BOTTOM_NAVIGATION
        }
    }
    Scaffold(
        topBar = {
            CityAppBar(
                onBackPressed = { viewModel.navigateToListPage() },
                isShowingListPage = uiState.isShowingListPage,
                windowSize = windowSize,
                currentTab = currentTab,
                currentPlace = uiState.currentPlace
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PlaceType.Parks.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = PlaceType.Parks.name) {
                CityHomeScreen(
                    navigationType = navigationType,
                    uiState = uiState,
                    onTabPressed = { placeType ->
                        viewModel.updateCurrentPlaceType(placeType)
                        navController.navigate(placeType.name)
                    },
                    currentTab = currentTab,
                    onCardClick = { place ->
                        viewModel.updateCurrentPlace(place)
                        viewModel.navigateToDetailPage()
                    },
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
            composable(route = PlaceType.ShoppingCentre.name) {
                CityHomeScreen(
                    navigationType = navigationType,
                    uiState = uiState,
                    onTabPressed = { placeType ->
                        viewModel.updateCurrentPlaceType(placeType)
                        navController.navigate(placeType.name)
                    },
                    currentTab = currentTab,
                    onCardClick = { place ->
                        viewModel.updateCurrentPlace(place)
                        viewModel.navigateToDetailPage()
                    },
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    }
}
