package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun TextWithCard(
    modifier: Modifier = Modifier,
    title : String = "",
    text : String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            )

            Text(
                text = text,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                color = textColor,
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

@Preview
@Composable
private fun preview(){
    BatikPediaTheme {
        TextWithCard(title = "yogyakarta", text = stringResource(id = R.string.tentang_yogya))
    }
}