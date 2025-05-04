package com.nhatnguyenba.ml_kit.domain.usecase

import com.nhatnguyenba.ml_kit.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(text: String) = repository.translate(text, "vi") // Default to Vietnamese
}