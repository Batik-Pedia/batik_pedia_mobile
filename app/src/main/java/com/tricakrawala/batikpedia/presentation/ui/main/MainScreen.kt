package com.tricakrawala.batikpedia.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.tricakrawala.batikpedia.presentation.model.main.MainViewModel
import com.tricakrawala.batikpedia.presentation.ui.BatikPediaApp
import com.tricakrawala.batikpedia.presentation.ui.SplashApp

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.getSession().observeAsState()
    if (state?.isNotNew == false) {
        SplashApp()
    } else {
        BatikPediaApp()
    }
}
