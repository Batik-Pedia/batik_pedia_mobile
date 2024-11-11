package com.tricakrawala.batikpedia.presentation.ui.screen.detailedukasi


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.model.detailedukasi.DetailKursusViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.ButtonLongGray
import com.tricakrawala.batikpedia.presentation.ui.components.KursusDetail
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.TextInfoKursus
import com.tricakrawala.batikpedia.presentation.ui.components.TextWithoutCard
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKursusScreen(
    idKursus: Long,
    viewModel: DetailKursusViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val uiStateKursus by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiStateKursus is UiState.Loading) {
            viewModel.getAllKursusDetail(idKursus)
        }
    }

    when (val kursus = uiStateKursus) {

        is UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                AlertDialog(
                    onDismissRequest = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent),
                ) {
                    LoadingData(Modifier.align(Alignment.Center), "Sedang Memuat Data..")
                }
            }
        }
        is UiState.Success -> {
            DetailKursusContent(
                imageKursus = kursus.data.image,
                titleKursus = kursus.data.namaKursus,
                detailKursus = kursus.data.deskripsi,
                kursusUrl = kursus.data.urlKursus,
                navController = navController
            )
        }

        else -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKursusContent(
    modifier: Modifier = Modifier,
    kursusUrl : String = "",
    imageKursus: String,
    detailKursus : String = "",
    titleKursus: String,
    navController: NavHostController,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
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
                    text = titleKursus,
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
                        tint = textColor,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.popBackStack() }
                            .background(Color.Transparent)

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
            KursusDetail(image = imageKursus, modifier = Modifier)

            Spacer(modifier = Modifier.height(8.dp))

            Box {
                TextInfoKursus(
                    title = titleKursus
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Box {
                TextWithoutCard(
                    title = stringResource(id = R.string.deskrip),
                    text = detailKursus
                )

                Text(
                    text = stringResource(id = R.string.selengkapnya),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = primary,
                    fontSize = 10.sp,
                    modifier = modifier
                        .padding(top = 36.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(kursusUrl)
                            }
                            context.startActivity(intent)
                        }
                )

            }
            ButtonLongGray(text = stringResource(id = R.string.ikuti_kursus), modifier = modifier.padding(top = 36.dp).clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(kursusUrl)
                }
                context.startActivity(intent)
            })
        }


    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    DetailKursusContent(
        imageKursus = "R.drawable.batik1",
        titleKursus = "Motif kawung",
        navController = rememberNavController()
    )
}