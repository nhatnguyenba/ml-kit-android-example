package com.nhatnguyenba.ml_kit.presentation.imagelabel

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nhatnguyenba.ml_kit.domain.model.Label

@Composable
fun LabelList(labels: List<Label>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(8.dp)
    ) {
        Text(
            text = "Detected Labels:",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        labels.forEach { label ->
            Log.d("LabelList", "Label: $label")
            Text(
                text = "${label.text} (${"%.2f".format(label.confidence * 100)}%)",
                color = Color.White
            )
        }
    }
}