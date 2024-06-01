package com.tricakrawala.batikpedia.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.screen.splash.SplashScreenFirst
import com.tricakrawala.batikpedia.presentation.ui.screen.splash.SplashScreenSecond
import com.tricakrawala.batikpedia.presentation.ui.screen.splash.SplashScreenThird

@Composable
fun SplashApp(
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashFirst.route
    ){
        composable(Screen.SplashFirst.route){
            SplashScreenFirst(navController = navController)
        }
        composable(Screen.SplashSecond.route){
            SplashScreenSecond(navController = navController)
        }
        composable(Screen.SplashThird.route){
            SplashScreenThird()
        }
    }
}