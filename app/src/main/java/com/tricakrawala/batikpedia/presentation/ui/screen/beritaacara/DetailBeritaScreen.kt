package com.tricakrawala.batikpedia.presentation.ui.screen.beritaacara

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.tricakrawala.batikpedia.presentation.model.berita.BeritaViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState

@Composable
fun DetailBeritaScreen(
    viewModel: BeritaViewModel = hiltViewModel(),
    idBerita : Int,
){
    val uiState by viewModel.uiStateDetail.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading){
            viewModel.getDetailBerita(idBerita)
        }
    }

    when(val berita = uiState){
        is UiState.Error -> {}
        is UiState.Success -> {
            DetailBeritaContent(url = berita.data.urlBerita)
        }

        else -> {}
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailBeritaContent(
    modifier: Modifier = Modifier,
    url : String,
){
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun Preview(){
    DetailBeritaContent(url = "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo")
}