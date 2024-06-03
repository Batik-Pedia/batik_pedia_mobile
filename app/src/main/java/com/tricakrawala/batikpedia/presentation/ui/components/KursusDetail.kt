package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
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
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor
import com.tricakrawala.batikpedia.presentation.ui.theme.textSecondary


@Composable
fun KursusDetail(
    modifier: Modifier = Modifier,
    image: String,
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
                contentDescription = "gambar provinsi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

        }
    }

@Composable
fun TextWithoutCard(
    modifier: Modifier = Modifier,
    title : String = "",
    text : String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            )

            Text(
                text = text,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = textSecondary,
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp, )
            )
        }else{
            Text(
                text = text,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = textColor,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}


@Composable
fun TextInfoKursus(
    modifier: Modifier = Modifier,
    title : String = ""
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Transparent)
    ) {
            Text(
                text = title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                color = textColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            Row(
                modifier = Modifier.padding(horizontal = 8.dp)
            ){
                Text(
                    text = "Rp100.000 - 200.000",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = primary,
                    fontSize = 20.sp,
                )

                Text(
                    text = "/ Jam",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Row(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                CardReview(text = "E-Certificate")
                Spacer(modifier = Modifier.width(14.dp))
                CardReview(text = "Batik")
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    BatikPediaTheme {
        KursusDetail(image = "R.drawable.yogyakarta")
    }
}