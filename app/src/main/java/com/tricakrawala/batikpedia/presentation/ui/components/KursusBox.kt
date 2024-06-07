package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor
import com.tricakrawala.batikpedia.utils.Utils.extractYoutubeVideoId


@Composable
fun KursusBox(
    modifier: Modifier = Modifier,
    kursus: String,
    image: String,
) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
    ) {


        Box(
            modifier = modifier
                .width(200.dp)
                .height(140.dp)
                .background(Color.White)

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "kursus",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.Center)
            )

            Text(
                text = kursus,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp, start = 4.dp, end = 4.dp)
                    .align(Alignment.BottomCenter)
            )


        }

    }

}

@Composable
fun VideoColumn(
    modifier: Modifier = Modifier,
    image: String,
    deskripsi: String,

    ) {

    val videoId = extractYoutubeVideoId(image)
    val thumbnailUrl = "https://img.youtube.com/vi/$videoId/0.jpg"

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(thumbnailUrl)
                .crossfade(true)
                .build(),
            contentDescription = "video membatik",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = deskripsi,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier
                .padding(start = 8.dp)
        )


    }

}

@Composable
@Preview(showBackground = true)
private fun preview() {
    BatikPediaTheme {
        KursusBox(kursus = "SuperProf", image = "R.drawable.kursus2")
    }
}