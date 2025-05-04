package com.nhatnguyenba.ml_kit.data

import android.util.Log
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import com.nhatnguyenba.ml_kit.domain.model.BarcodeResult
import com.nhatnguyenba.ml_kit.domain.repository.BarcodeRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BarcodeRepositoryImpl @Inject constructor(
    private val scanner: BarcodeScanner
) : BarcodeRepository {
    override suspend fun scanBarcode(image: InputImage): Result<List<BarcodeResult>> {
        return try {
            val barcodes = suspendCancellableCoroutine { continuation ->
                scanner.process(image).addOnSuccessListener { barcodes ->
                    continuation.resume(barcodes)
                }.addOnFailureListener { ex ->
                    continuation.resumeWithException(ex)
                }
            }
            Result.success(barcodes.map {
                Log.d("BarcodeRepositoryImpl", "Barcode: $it")
                BarcodeResult(it.rawValue ?: "", it.format)
            })
        } catch (e: Exception) {
            Log.e("BarcodeRepositoryImpl", "Error scanning barcode", e)
            Result.failure(e)
        }
    }
}