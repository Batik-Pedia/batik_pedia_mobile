package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.primary

@Composable
fun CardReview(
    modifier: Modifier = Modifier,
    text : String
){
   Card(
       colors = CardDefaults.cardColors(primary)
   ) {
       Text(
           text = text,
           fontFamily = poppinsFontFamily,
           fontWeight = FontWeight.Normal,
           color = Color.White,
           fontSize = 12.sp,
           modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
       )
   }
}

@Composable
@Preview
private fun Preview(){
    CardReview(text = "E-Certificate")
}