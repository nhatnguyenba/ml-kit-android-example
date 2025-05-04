package com.nhatnguyenba.ml_kit.domain.repository

import com.nhatnguyenba.ml_kit.domain.model.TranslationResult

interface TranslationRepository {
    suspend fun translate(text: String, targetLang: String): Result<TranslationResult>
    fun setTargetLanguage(langCode: String)
}