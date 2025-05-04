package com.nhatnguyenba.ml_kit.presentation.imagelabel

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhatnguyenba.ml_kit.presentation.camera.CameraPreview
import com.nhatnguyenba.ml_kit.presentation.components.CameraPermissionHandler

@OptIn(ExperimentalGetImage::class)
@Composable
fun ImageLabelingScreen(viewModel: ImageLabelingViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val labels by viewModel.labels

    CameraPermissionHandler {
        Box(modifier = Modifier.fillMaxSize()) {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                onImageAvailable = { imageProxy ->
                    viewModel.processImage(imageProxy)
                }
            )

            LabelList(labels = labels)
        }
    }
}