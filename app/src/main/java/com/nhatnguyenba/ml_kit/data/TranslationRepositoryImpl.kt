package com.nhatnguyenba.ml_kit.data

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.Translator
import com.nhatnguyenba.ml_kit.domain.model.TranslationResult
import com.nhatnguyenba.ml_kit.domain.repository.TranslationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class TranslationRepositoryImpl @Inject constructor(
    private val translator: Translator
) : TranslationRepository {
    override suspend fun translate(text: String, targetLang: String): Result<TranslationResult> {
        return suspendCancellableCoroutine { continuation ->
            // Bước 1: Download model
            val conditions = DownloadConditions.Builder()
                .requireWifi()
                .build()
            translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    // Bước 2: Dịch text khi đã có model
                    translator.translate(text)
                        .addOnSuccessListener { translatedText ->
                            continuation.resume(
                                Result.success(
                                    TranslationResult(
                                        text,
                                        translatedText
                                    )
                                )
                            )
                        }
                        .addOnFailureListener { translateError ->
                            continuation.resume(Result.failure(translateError))
                        }
                }
                .addOnFailureListener { downloadError ->
                    continuation.resume(Result.failure(downloadError))
                }

            // Xử lý hủy coroutine
            continuation.invokeOnCancellation {

            }
        }
    }

    override fun setTargetLanguage(langCode: String) {
        // Implement language change logic
    }
}