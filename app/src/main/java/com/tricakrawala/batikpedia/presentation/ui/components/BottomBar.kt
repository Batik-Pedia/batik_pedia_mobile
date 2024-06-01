package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.navigation.NavigationItem
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.theme.bottomNavigation
import com.tricakrawala.batikpedia.presentation.ui.theme.iconBottom
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    backgroundColor: Color = bottomNavigation
) {

    BottomAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        cutoutShape = CircleShape
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_beranda),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_beranda),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_katalog),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_katalog),
                screen = Screen.Katalog
            ),
            NavigationItem(
                title = "",
                icon = Icons.Default.Close,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_edukasi),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_edukasi),
                screen = Screen.Edukasi
            ),
            NavigationItem(
                title = stringResource(R.string.menu_wisata),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_wisata),
                screen = Screen.Wisata
            ),
        )

        val navigationItemsFilled = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_beranda),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_beranda_filled),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_katalog),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_katalog_filled),
                screen = Screen.Katalog
            ),
            NavigationItem(
                title = "",
                icon = Icons.Default.Close,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_edukasi),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_edukasi_filled),
                screen = Screen.Edukasi
            ),
            NavigationItem(
                title = stringResource(R.string.menu_wisata),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bottom_wisata_filled),
                screen = Screen.Wisata
            ),
        )

        navigationItems.forEachIndexed { index, item ->
            val filledItem = navigationItemsFilled[index]
            if (index != 2) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.screen.route) filledItem.icon else item.icon,
                            contentDescription = item.title,
                            tint = if (currentRoute == item.screen.route) primary else iconBottom
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            color = if (currentRoute == item.screen.route) primary else iconBottom,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )
            } else {
                BottomNavigationItem(
                    icon = {},
                    label = { },
                    selected = false,
                    onClick = { },
                    enabled = false
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    BottomBar(navController = rememberNavController(), modifier = Modifier.background(Color.White))
}