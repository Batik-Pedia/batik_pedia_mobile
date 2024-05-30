package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun AtlasItem(
    modifier: Modifier = Modifier,
    image : Int,
    nusantara : String,
){

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.provinsi_, nusantara),
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier.padding( start = 8.dp, end = 8.dp , top = 16.dp)
        )

        Image(painter = painterResource(id = image),  contentDescription = "",modifier = Modifier.padding( start = 8.dp, end = 8.dp, bottom = 8.dp).width(304.dp).height(196.dp))
    }
    
}

@Composable
@Preview(showBackground = true)
private fun Preview(){
    BatikPediaTheme {
        AtlasItem(image= R.drawable.peta1, nusantara = "Yogyakarta")
    }
}