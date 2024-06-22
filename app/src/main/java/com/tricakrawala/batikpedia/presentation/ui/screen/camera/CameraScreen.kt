package com.tricakrawala.batikpedia.presentation.ui.screen.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.components.ButtonLongGray
import com.tricakrawala.batikpedia.presentation.ui.components.CircleShapeButton
import com.tricakrawala.batikpedia.presentation.ui.components.ImageResult
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    CameraContent(modifier = modifier, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }
    var predictionResult by remember { mutableStateOf("") }
    var hasCameraPermission by remember { mutableStateOf(false) }
    var isCameraActive by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }
    var isProcessing by remember { mutableStateOf(false) } // State untuk memantau proses identifikasi

    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_BACK) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }
    var cameraProvider: ProcessCameraProvider? by remember { mutableStateOf(null) }
    var flashStatus by remember { mutableStateOf(false) }

    var buttonText by remember { mutableStateOf("Identifikasi") }

    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    var camera: Camera? by remember { mutableStateOf(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
        }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            hasCameraPermission = true
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(hasCameraPermission, lensFacing) {
        if (hasCameraPermission) {
            cameraProvider = context.getCameraProvider()
            cameraProvider?.unbindAll()
            camera = cameraProvider?.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)!!
            camera!!.cameraInfo.hasFlashUnit()
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
    }

    LaunchedEffect(isProcessing) {
        if (isProcessing) {
            delay(3000) // Delay 3 detik
            isLoading = false
            isProcessing = false
        }
    }

    fun toggleFlash() {
        flashStatus = !flashStatus
        camera?.cameraControl?.enableTorch(flashStatus)
    }

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                view.visibility = if (isCameraActive) View.VISIBLE else View.INVISIBLE
            }
        )

        CenterAlignedTopAppBar(
            title = {},
            navigationIcon = {
                CircleShapeButton(
                    image = ImageVector.vectorResource(id = R.drawable.ic_x),
                    contentDesc = "Close",
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.padding(start = 12.dp)
                )
            },
            actions = {
                CircleShapeButton(
                    image = ImageVector.vectorResource(id = R.drawable.ic_question),
                    contentDesc = "Whats upp",
                    onClick = {},
                    modifier = Modifier.padding(end = 8.dp)
                )
                CircleShapeButton(
                    image = ImageVector.vectorResource(id = R.drawable.ic_camera),
                    contentDesc = "Pick image",
                    onClick = {
                        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT
                        else CameraSelector.LENS_FACING_BACK
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )

                CircleShapeButton(
                    image = ImageVector.vectorResource(id = if (flashStatus) R.drawable.ic_lightning else R.drawable.ic_lightningslash),
                    contentDesc = "Flash",
                    onClick = {
                        toggleFlash()
                    },
                    modifier = Modifier.padding(end = 12.dp)
                )
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
        )

        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.Center)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                LoadingData(message = "Mengindentifikasi batik..")
            } else {
                capturedImage?.let { image ->
                    ImageResult(image = image, result = predictionResult)
                } ?: run {
                    Image(
                        painter = painterResource(id = R.drawable.scan_border),
                        contentDescription = "",
                        Modifier.fillMaxSize()
                    )
                }
            }
        }

        ButtonLongGray(
            text = buttonText,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .clickable {
                    if (isCameraActive) {
                        isLoading = true // Menampilkan loading indicator
                        isProcessing = true
                        captureImage(
                            imageCapture,
                            context,
                            onImageCaptured = { capturedBitmap, result ->
                                capturedImage = capturedBitmap
                                predictionResult = result
                                isCameraActive = false
                                buttonText = "Identifikasi Kembali"
                            },
                        )
                    } else {
                        capturedImage = null
                        predictionResult = ""
                        isCameraActive = true
                        buttonText = "Identifikasi"
                    }
                }
        )
    }
}



private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(with(cameraProvider) { get() })
            }, ContextCompat.getMainExecutor(this))
        }
    }
