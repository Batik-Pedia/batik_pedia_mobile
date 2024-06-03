package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily

@Composable
fun CardBerita(
    modifier: Modifier = Modifier,
    image: String,
    text: String,
) {
    Column(
        modifier = modifier,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(165.dp)
                .clip(RoundedCornerShape(16.dp))
                ,

        )

        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            color = background2,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 12.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun preview() {
    BatikPediaTheme {
        Box {
            CardBerita(
                image = "R.drawable.fake_berita_image",
                text = "Melihat Proses Pembuatan batik"
            )
        }
    }
}