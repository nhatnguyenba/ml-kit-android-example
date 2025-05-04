package com.nhatnguyenba.ml_kit.presentation.translation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TranslationScreen(viewModel: TranslationViewModel = hiltViewModel()) {
    var textToTranslate by remember { mutableStateOf("") }
    val translationResult by viewModel.translationResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = textToTranslate,
            onValueChange = { textToTranslate = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter text in English to translate") }
        )

        Button(
            onClick = { viewModel.translate(textToTranslate) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Translate to Vietnamese")
        }

        translationResult?.let {
            TranslationResultView(result = it)
        }
    }
}