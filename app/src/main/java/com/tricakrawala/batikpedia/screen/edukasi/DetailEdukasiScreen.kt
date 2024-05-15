package com.tricakrawala.batikpedia.screen.edukasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.common.UiState
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailEdukasiScreen(
    modifier: Modifier = Modifier,
    idKursus : Long,
    navController : NavHostController,
    viewModel: EdukasiViewModel = koinViewModel(),
){
    val uiState by viewModel.uiStateKursusById.collectAsState(initial = UiState.Loading)

    when(val kursus = uiState){
        is UiState.Error -> {}
        is UiState.Success -> {
            DetailEdukasiContent(kursus = kursus.data.kursus, navController = navController, image = kursus.data.image)
        }

        else -> {}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailEdukasiContent(
    modifier: Modifier = Modifier,
    kursus : String,
    image : Int,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())

    ) {
        Image(
            painter = painterResource(id = R.drawable.ornamen_batik_beranda),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(180.dp)

        )

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = kursus,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 16.sp
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 88.dp, start = 24.dp, end = 24.dp)
        ) {


            }
        }
    }



@Composable
@Preview(showBackground = true)
private fun Preview(){
    BatikPediaTheme {
        DetailEdukasiContent(kursus = "SuperProf", navController = rememberNavController(), image = 2)
    }
}

