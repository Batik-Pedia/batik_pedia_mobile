package com.tricakrawala.batikpedia.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tricakrawala.batikpedia.presentation.model.main.MainViewModel
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

            setContent {
                BatikPediaTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                      MainScreen(viewModel)
                    }
                }
            }
    }
}



