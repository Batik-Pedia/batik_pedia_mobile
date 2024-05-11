package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor

@Composable
fun KursusBox(
    modifier: Modifier = Modifier,
    kursus: String,
    image: Int,
) {
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
    ) {


        Box(
            modifier = modifier
                .width(200.dp)
                .height(150.dp)
                .background(Color.White)

        ) {
            Image(
                painter = painterResource(id = image),
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
    image: Int,
    deskripsi: String,

    ) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = image),
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
        NusantaraItemRow(provinsi = "Yogyakarta", image = R.drawable.yogyakarta)
    }
}