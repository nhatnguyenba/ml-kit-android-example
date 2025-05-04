package com.nhatnguyenba.ml_kit.domain.repository

import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.model.Label

interface ImageLabelingRepository {
    suspend fun labelImage(image: InputImage): Result<List<Label>>
}