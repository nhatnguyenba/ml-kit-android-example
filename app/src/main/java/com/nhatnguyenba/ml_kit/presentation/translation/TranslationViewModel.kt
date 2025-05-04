package com.nhatnguyenba.ml_kit.presentation.translation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhatnguyenba.ml_kit.domain.model.TranslationResult
import com.nhatnguyenba.ml_kit.domain.usecase.TranslateTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translateUseCase: TranslateTextUseCase
) : ViewModel() {
    private val _translationResult = mutableStateOf<TranslationResult?>(null)
    val translationResult: State<TranslationResult?> = _translationResult

    fun translate(text: String) {
        viewModelScope.launch {
            translateUseCase(text).onSuccess {
                _translationResult.value = it
            }.onFailure {
                // Handle error
            }
        }
    }
}