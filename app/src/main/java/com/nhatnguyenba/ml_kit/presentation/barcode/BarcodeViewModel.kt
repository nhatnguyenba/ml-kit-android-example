package com.nhatnguyenba.ml_kit.presentation.barcode

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.model.BarcodeResult
import com.nhatnguyenba.ml_kit.domain.usecase.ScanBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarcodeViewModel @Inject constructor(
    private val scanUseCase: ScanBarcodeUseCase
) : ViewModel() {
    private val _result = mutableStateOf<List<BarcodeResult>?>(null)
    val result: State<List<BarcodeResult>?> = _result

    @OptIn(ExperimentalGetImage::class)
    fun processImage(image: ImageProxy) {
        viewModelScope.launch {
            val imageInput = InputImage.fromMediaImage(
                image.image!!,
                image.imageInfo.rotationDegrees
            )
            scanUseCase(imageInput).onSuccess {
                _result.value = it
                image.close()
            }.onFailure {
                // Handle error
                image.close()
            }
        }
    }
}