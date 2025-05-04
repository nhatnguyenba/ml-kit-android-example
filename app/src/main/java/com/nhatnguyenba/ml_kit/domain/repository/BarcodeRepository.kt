package com.nhatnguyenba.ml_kit.domain.repository

import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.model.BarcodeResult

interface BarcodeRepository {
    suspend fun scanBarcode(image: InputImage): Result<List<BarcodeResult>>
}