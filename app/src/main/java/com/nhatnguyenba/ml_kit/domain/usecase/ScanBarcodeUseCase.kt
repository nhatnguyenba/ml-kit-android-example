package com.nhatnguyenba.ml_kit.domain.usecase

import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.repository.BarcodeRepository
import javax.inject.Inject

class ScanBarcodeUseCase @Inject constructor(
    private val repository: BarcodeRepository
) {
    suspend operator fun invoke(image: InputImage) = repository.scanBarcode(image)
}