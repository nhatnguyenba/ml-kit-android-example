package com.nhatnguyenba.ml_kit.presentation.imagelabel

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.model.Label
import com.nhatnguyenba.ml_kit.domain.usecase.LabelImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageLabelingViewModel @Inject constructor(
    private val labelUseCase: LabelImageUseCase
) : ViewModel() {
    private val _labels = mutableStateOf<List<Label>>(emptyList())
    val labels: State<List<Label>> = _labels

    @OptIn(ExperimentalGetImage::class)
    fun processImage(image: ImageProxy) {
        viewModelScope.launch {
            val imageInput = InputImage.fromMediaImage(
                image.image!!,
                image.imageInfo.rotationDegrees
            )
            labelUseCase(imageInput).onSuccess {
                Log.d("ImageLabelingViewModel", "Labels: $it")
                _labels.value = it.sortedByDescending { label -> label.confidence }
                image.close()
            }.onFailure {
                // Handle error
                Log.e("ImageLabelingViewModel", "Error processing image", it)
                image.close()
            }
        }
    }
}