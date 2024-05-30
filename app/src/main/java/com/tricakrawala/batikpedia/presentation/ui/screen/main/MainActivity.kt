package com.tricakrawala.batikpedia.presentation.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tricakrawala.batikpedia.presentation.model.main.MainViewModel
import com.tricakrawala.batikpedia.presentation.ui.BatikPediaApp
import com.tricakrawala.batikpedia.presentation.ui.SplashApp
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getSession().observe(this) { state ->

            setContent {
                BatikPediaTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        if (!state.isNotNew) {
                            SplashApp()
                        } else {
                            BatikPediaApp()
                        }
                    }
                }
            }

        }


    }
}



