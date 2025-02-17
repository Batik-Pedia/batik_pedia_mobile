package com.tricakrawala.batikpedia.presentation.ui.screen.detailedukasi

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
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
import com.tricakrawala.batikpedia.data.resource.remote.response.ValuesItem
import com.tricakrawala.batikpedia.presentation.model.edukasi.EdukasiViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.VideoColumn
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListVideoScreen(
    viewModel: EdukasiViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val uiStateVideoMembatik by viewModel.uiStateVideoMembatik.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiStateVideoMembatik is UiState.Loading) {
            viewModel.getAllVideo()
        }
    }

    when (val video = uiStateVideoMembatik) {
        is UiState.Success -> {
            VideoListContent(
                listVideoMembatik = video.data,
                navController = navController
            )

        }

        is UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                BasicAlertDialog (
                    onDismissRequest = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent),
                ) {
                    LoadingData(Modifier.align(Alignment.Center), "Sedang Memuat Data..")
                }
            }
        }

        else -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListContent(
    navController: NavHostController,
    listVideoMembatik: List<ValuesItem>,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()

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
                    text = stringResource(id = R.string.edukasi),
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
                        modifier = Modifier.size(24.dp),
                        tint = textColor
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
                .fillMaxSize()
                .padding(top = 88.dp, start = 24.dp, end = 24.dp)


        )
        {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 4.dp, start = 4.dp, bottom = 4.dp),
            ) {
                items(listVideoMembatik) { data ->
                    VideoColumn(
                        image = data.imgVideo,
                        deskripsi = data.judulVideo,
                        modifier =  Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.imgVideo))
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }

    }
}


@Preview
@Composable
private fun Preview() {
    BatikPediaTheme {
        VideoListContent(
            navController = rememberNavController(),
            listVideoMembatik = emptyList()
        )
    }
}