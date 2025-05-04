package com.nhatnguyenba.ml_kit.data

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.nhatnguyenba.ml_kit.domain.model.Label
import com.nhatnguyenba.ml_kit.domain.repository.ImageLabelingRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ImageLabelingRepositoryImpl @Inject constructor(
    private val labeler: ImageLabeler
) : ImageLabelingRepository {
    override suspend fun labelImage(image: InputImage): Result<List<Label>> {
        return try {
            val labels = suspendCancellableCoroutine { continuation ->
                labeler.process(image)
                    .addOnSuccessListener { mlLabels ->
                        val domainLabels = mlLabels.map { mlLabel ->
                            Label(
                                text = mlLabel.text,
                                confidence = mlLabel.confidence,
                            )
                        }
                        continuation.resume(domainLabels)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }
            Result.success(labels)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}