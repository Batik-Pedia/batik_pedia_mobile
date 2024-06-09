package com.tricakrawala.batikpedia.presentation.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AtlasItem(
    modifier: Modifier = Modifier,
    latitude : Double,
    longitude : Double,
    nusantara : String,
){
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(latitude, longitude), 15f
        )
    }
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )




    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

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

        when {
            locationPermissionState.status.isGranted -> {
                GoogleMap(
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .width(304.dp).height(196.dp),
                    cameraPositionState = cameraPositionState
                ) {
                    val customMarkerIcon = bitmapDescriptorFromVector(context, R.drawable.marker_maps)
                    Marker(
                        state = MarkerState(position = LatLng(latitude, longitude)),
                        title = "Marker Title",
                        snippet = "Marker Snippet",
                        icon = customMarkerIcon
                    )
                }
            }
            locationPermissionState.status.shouldShowRationale -> {
                Text(
                    text = "Location permission is needed to show the map.",
                    modifier = Modifier.padding(8.dp)
                )
            }
            else -> {
                Text(
                    text = "Requesting location permission...",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }

}

@SuppressLint("UseCompatLoadingForDrawables")
fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    val vectorDrawable: Drawable? = context.getDrawable(vectorResId)
    vectorDrawable?.let {
        val bitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        it.setBounds(0, 0, canvas.width, canvas.height)
        it.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
    return null
}
