package com.tricakrawala.batikpedia.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.components.BottomBar
import com.tricakrawala.batikpedia.presentation.ui.screen.beritaacara.BeritaAcaraScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.beritaacara.DetailBeritaScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.camera.CameraScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.detailbatik.DetailMotifBatikFullScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.detailbatik.DetailMotifScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.detailedukasi.DetailKursusScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.detailedukasi.ListKursusScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.detailedukasi.ListVideoScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.edukasi.EdukasiScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.filter.FilterScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.home.HomeScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.katalog.KatalogScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.provinsi.DetailProvinsiScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.provinsi.ListProvinsiScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.provinsi.WisataProvinsiScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.wisata.DetailWisataScreen
import com.tricakrawala.batikpedia.presentation.ui.screen.wisata.WisataScreen
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.utils.Utils

@Composable
fun BatikPediaApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route

    val shouldShowBottomBar = currentRoute !in Utils.listScreenWithoutBottomBar

    Scaffold(
        contentColor = background2,
        backgroundColor = background2,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (shouldShowBottomBar) {
                Box(modifier = Modifier.offset(y = (-5).dp)) {
                    FloatingActionButton(
                        onClick = { navController.navigate(Screen.Camera.route) },
                        shape = CircleShape,
                        containerColor = primary,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottom_scan),
                            contentDescription = "Scan",
                            tint = Color.White
                        )
                    }
                }
            }

        },

        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBar(navController, modifier = Modifier.navigationBarsPadding())
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    navigateToNusantara = { idNusantara ->
                        navController.navigate(
                            Screen.DetailProvinsi.createRoute(
                                idNusantara
                            )
                        )
                    },
                )
            }

            composable(Screen.Katalog.route) {
                KatalogScreen(navToDetail = { idBatik ->
                    navController.navigate(Screen.DetailBatik.createRoute(idBatik))
                }, navController = navController)
            }
            composable(
                Screen.DetailBatik.route,
                arguments = listOf(navArgument("idBatik") { type = NavType.IntType }),
            )
            {
                val id = it.arguments?.getInt("idBatik") ?: 0
                DetailMotifScreen(
                    idBatik = id,
                    navController = navController,
                    navToBatikFullDetail = { idBatikFull ->
                        navController.navigate(Screen.DetailBatikFull.createRoute(id, idBatikFull))
                    })
            }
            composable(
                Screen.DetailBatikFull.route,
                arguments = listOf(navArgument("idBatikFull") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("idBatikFull") ?: 0
                DetailMotifBatikFullScreen(idBatik = id, navController = navController)
            }

            composable(
                Screen.ToListProvinsi.route,

                ) {

                ListProvinsiScreen(navigateToDetail = { idNusantara ->
                    navController.navigate(Screen.DetailProvinsi.createRoute(idNusantara))
                }, navController = navController)
            }
            composable(
                Screen.DetailProvinsi.route,
                arguments = listOf(navArgument("idNusantara") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("idNusantara") ?: -1L
                DetailProvinsiScreen(idProvinsi = id, navigateToWisata = { idWisata ->
                    navController.navigate(Screen.DetailWisataByProvinsi.createRoute(id, idWisata))
                }, navToDetailBatik = {idBatik ->
                    navController.navigate(Screen.DetailBatik.createRoute(idBatik))
                }, navController = navController)
            }

            composable(
                Screen.DetailWisataByProvinsi.route,
                arguments = listOf(navArgument("idWisata") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("idWisata") ?: -1L
                WisataProvinsiScreen(idWisata = id, navController = navController)
            }

            composable(Screen.Wisata.route) {
                WisataScreen(navigateToDetail = { idWisata ->
                    navController.navigate(Screen.DetailWisata.createRoute(idWisata))
                })
            }

            composable(
                Screen.DetailWisata.route,
                arguments = listOf(navArgument("idWisata") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("idWisata") ?: -1L
                DetailWisataScreen(idWisata = id, navController = navController)
            }

            composable(Screen.Berita.route) {
                BeritaAcaraScreen(navController = navController, navigateToDetail = { idBerita ->
                    navController.navigate(Screen.DetailBerita.createRoute(idBerita))
                })
            }


            composable(Screen.Filter.route) {
                FilterScreen(navController = navController)
            }

            composable(Screen.Edukasi.route) {
                EdukasiScreen(navigateToDetail = { idKursus ->
                    navController.navigate(Screen.DetailKursus.createRoute(idKursus))
                }, navController = navController)
            }
            composable(
                Screen.DetailKursus.route,
                arguments = listOf(navArgument("idKursus") { type = NavType.LongType }),
            )
            {
                val id = it.arguments?.getLong("idKursus") ?: 0L
                DetailKursusScreen(
                    idKursus = id,
                    navController = navController,)
            }
            composable(
                Screen.DetailBatikFull.route,
                arguments = listOf(navArgument("idBatikFull") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("idBatikFull") ?: 0
                DetailMotifBatikFullScreen(idBatik = id, navController = navController)
            }

            composable(Screen.Edukasi.route) {
                EdukasiScreen(navController = navController, navigateToDetail = { idKursus ->
                    navController.navigate(Screen.DetailEdukasi.createRoute(idKursus))
                })
            }

            composable(
                Screen.ToListKursus.route,
            ) {
                ListKursusScreen(navigateToDetail = { idKursus ->
                    navController.navigate(Screen.DetailKursus.createRoute(idKursus))
                }, navController = navController)
            }
            composable(Screen.VideoEdukasi.route) {
                ListVideoScreen(navController = navController)
            }

            composable(Screen.Camera.route) {
                CameraScreen(navController = navController)
            }

            composable(
                Screen.DetailBerita.route,
                arguments = listOf(navArgument("idBerita") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("idBerita") ?: 0
                DetailBeritaScreen(idBerita = id)
            }

        }
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() {
    BatikPediaApp()
}