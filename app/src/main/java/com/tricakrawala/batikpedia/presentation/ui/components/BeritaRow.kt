package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun BeritaRow(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    time: String,
    lokasi: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = "gambar berita",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = title,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                contentDescription = "icon calendar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = time,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = textColor,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_maps),
                contentDescription = "icon maps"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = lokasi,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = textColor,
            )
        }

    }

}


@Composable
@Preview(showBackground = true)
private fun Preview() {
    BatikPediaTheme {
        BeritaRow(
            image = "R.drawable.fake_berita_image",
            title = "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            time = "04 Apr 2024 08.00",
            lokasi = "Solo, Indonesia"
        )
    }
}