package com.tricakrawala.batikpedia.presentation.ui.screen.mainsplash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.main.MainActivity
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BatikPediaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val scale = remember { Animatable(0f) }

                    LaunchedEffect(
                        key1 = true,
                        block = {
                            scale.animateTo(
                                targetValue = 0.9f,
                                animationSpec = tween(
                                    durationMillis = 800
                                )
                            )
                            delay(2000L)
                            navigateToNextScreen()
                        }
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(background2)

                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                        ) {


                            Image(
                                painter = painterResource(id = R.drawable.cloud_splash),
                                contentDescription = "Logo Batik Pedia",
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(top = 60.dp)
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.logo_splash),
                            contentDescription = "logo splash",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 80.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.teks_splash),
                            contentDescription = "teks ",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp)
                        )


                        Spacer(modifier = Modifier.weight(1f))


                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .navigationBarsPadding(),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cloud_splash_bottom),
                                contentDescription = "Logo batik bawah",
                                modifier = Modifier
                                    .align(Alignment.BottomStart)

                            )

                        }

                    }

                }
            }
        }

    }

    private fun navigateToNextScreen() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
