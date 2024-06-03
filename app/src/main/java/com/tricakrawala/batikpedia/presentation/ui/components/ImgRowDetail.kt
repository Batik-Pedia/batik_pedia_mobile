package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme

@Composable
fun ImgRowDetail(
    modifier: Modifier = Modifier,
    image : String
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(88.dp)
            .height(47.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
@Preview
private fun preview(){
    BatikPediaTheme {
        ImgRowDetail(image = "R.drawable.batik1")
    }

}