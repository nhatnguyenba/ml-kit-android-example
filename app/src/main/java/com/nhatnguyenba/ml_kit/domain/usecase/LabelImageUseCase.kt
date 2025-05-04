package com.nhatnguyenba.ml_kit.domain.usecase

import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.repository.ImageLabelingRepository
import javax.inject.Inject

class LabelImageUseCase @Inject constructor(
    private val repository: ImageLabelingRepository
) {
    suspend operator fun invoke(image: InputImage) = repository.labelImage(image)
}