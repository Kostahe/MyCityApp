package com.example.mycity.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.mycity.R
import com.example.mycity.data.Place
import com.example.mycity.data.PlaceType
import com.example.mycity.ui.utils.CityNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityHomeScreen(
    navigationType: CityNavigationType,
    uiState: CityUiState,
    onTabPressed: (PlaceType) -> Unit,
    currentTab: PlaceType,
    onCardClick: (Place) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            placeType = PlaceType.Parks,
            icon = ImageVector.vectorResource(id = R.drawable.park_icon),
            text = stringResource(id = R.string.tab_parks)
        ),
        NavigationItemContent(
            placeType = PlaceType.ShoppingMalls,
            icon = Icons.Default.ShoppingCart,
            text = stringResource(id = R.string.tab_shopping_malls)
        )
    )

    if (navigationType == CityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        val navigationDrawerContentDescription = stringResource(R.string.navigation_drawer)
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
                    CityNavigationDrawerContent(
                        currentTab = currentTab,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier
                            .wrapContentWidth()
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                            .testTag(navigationDrawerContentDescription)
                    )
                }
            },
        )  {
            CityListAndPlaceDetail(
                uiState = uiState,
                onCardClick = onCardClick
            )
        }
    } else {
        if (uiState.isShowingListPage) {
            CityAppContent(
                navigationType = navigationType,
                currentTab = currentTab,
                onTabPressed = onTabPressed,
                onCardClick = onCardClick,
                navigationItemContentList = navigationItemContentList,
                uiState = uiState,
                modifier = modifier
            )
        } else {
            PlaceDetail(
                selectedPlace = uiState.currentPlace,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun CityAppContent(
    navigationType: CityNavigationType,
    currentTab: PlaceType,
    onTabPressed: (PlaceType) -> Unit,
    onCardClick: (Place) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    uiState: CityUiState,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = navigationType == CityNavigationType.NAVIGATION_RAIL) {
                CityNavigationRail(
                    currentTab = currentTab,
                    onTabPressed = onTabPressed,
                    navigationItemContentList = navigationItemContentList
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                CityList(
                    uiState = uiState,
                    onCardClick = { place: Place ->
                        onCardClick(place)
                    }
                )

                AnimatedVisibility(
                    visible = navigationType == CityNavigationType.BOTTOM_NAVIGATION
                ) {
                    CityBottomNavigation(
                        currentTab = currentTab,
                        onTabPressed = onTabPressed,
                        navigationItemContentList = navigationItemContentList,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun CityNavigationRail(
    currentTab: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        navigationItemContentList.forEach { navItem ->
            NavigationRailItem (
                selected = currentTab == navItem.placeType,
                onClick = { onTabPressed(navItem.placeType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@Composable
fun CityBottomNavigation(
    currentTab: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        navigationItemContentList.forEach { navItem ->
            NavigationBarItem(
                selected = currentTab == navItem.placeType,
                onClick = { onTabPressed(navItem.placeType) } ,
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityNavigationDrawerContent(
    currentTab: PlaceType,
    onTabPressed: ((PlaceType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        navigationItemContentList.forEach { navItem ->
            NavigationDrawerItem(
                label = {
                    Text(
                       text = navItem.text,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.drawer_padding_header))
                    )
                },
                selected = currentTab == navItem.placeType,
                onClick = { onTabPressed(navItem.placeType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                )
            )
        }
    }
}


data class NavigationItemContent(
    val placeType: PlaceType,
    val icon: ImageVector,
    val text: String
)