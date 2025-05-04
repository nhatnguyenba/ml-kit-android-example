package com.nhatnguyenba.ml_kit.presentation.barcode

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhatnguyenba.ml_kit.presentation.camera.CameraPreview
import com.nhatnguyenba.ml_kit.presentation.components.CameraPermissionHandler

@OptIn(ExperimentalGetImage::class)
@Composable
fun BarcodeScreen(viewModel: BarcodeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var showResult by remember { mutableStateOf(true) }

    CameraPermissionHandler {
        Box(modifier = Modifier.fillMaxSize()) {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onImageAvailable = { imageProxy ->
                    viewModel.processImage(imageProxy)
                }
            )

            viewModel.result.value?.let { result ->
                if (showResult) {
                    BarcodeResultDialog(
                        result = result,
                        onDismiss = { showResult = false }
                    )
                }
            }
        }
    }
}