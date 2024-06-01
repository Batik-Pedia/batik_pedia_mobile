package com.tricakrawala.batikpedia.presentation.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.components.ButtonNextSplah
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary


@Composable
fun SplashScreenFirst(
    navController: NavHostController
) {
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
                painter = painterResource(id = R.drawable.logo_batik_pedia),
                contentDescription = "Logo Batik Pedia",
                modifier = Modifier
                    .padding(start = 36.dp, top = 36.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.cloud_splash),
                contentDescription = "Logo Batik Pedia",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 60.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.wayang),
            contentDescription = "logo wayang",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.selamat_datang),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )

        Text(
            text = stringResource(id = R.string.text_splash2),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
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

            Image(
                painter = painterResource(id = R.drawable.splash_indicator_1),
                contentDescription = "indicator",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 36.dp)

            ) {
                ButtonNextSplah(
                    onClick = { navController.navigate(Screen.SplashSecond.route) },
                    color = primary,
                    text = stringResource(id = R.string.selanjutnya),
                    textColor = Color.White
                )
                Spacer(modifier = Modifier.width(44.dp))
                ButtonNextSplah(
                    onClick = { navController.navigate(Screen.SplashThird.route) },
                    color = Color.White,
                    text = stringResource(id = R.string.lewati),
                    textColor = primary
                )
            }

        }

    }
}


@Composable
@Preview(showBackground = true)
private fun PreviewOne() {
    BatikPediaTheme {
        SplashScreenFirst(navController = rememberNavController())
    }
}



