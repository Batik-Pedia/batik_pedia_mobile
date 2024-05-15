package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor
import com.tricakrawala.batikpedia.ui.theme.textSecondary


@Composable
fun KursusDetail(
    modifier: Modifier = Modifier,
    image: Int,
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "gambar provinsi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Image(
                painter = painterResource(id = R.drawable.splash_indicator_1),
                contentDescription = "indicator",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
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
    title : String = "",
    text : String
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
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            )
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            var rating1 by remember {
                mutableDoubleStateOf(3.5)
            }
            RatingBar(
                modifier = Modifier
                    .size(25.dp),
                rating = rating1,
                onRatingChanged = {
                    rating1 = it
                },
                starsColor = Color.Yellow
            )

            Spacer(modifier = Modifier.height(30.dp))


        }


    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    BatikPediaTheme {
        KursusDetail(image = R.drawable.yogyakarta)
    }
}