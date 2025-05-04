package com.nhatnguyenba.ml_kit.presentation.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.nhatnguyenba.ml_kit.utils.hasCameraPermission

@Composable
fun CameraPermissionHandler(
    content: @Composable (Boolean) -> Unit
) {
    val context = LocalContext.current
    var showRationale by remember { mutableStateOf(false) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted && !context.hasCameraPermission()) {
            showRationale = true
        }
    }

    val hasPermission = remember {
        derivedStateOf { context.hasCameraPermission() }
    }

    LaunchedEffect(Unit) {
        if (!hasPermission.value) {
            cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    if (showRationale) {
        PermissionRationaleDialog(
            onConfirm = { cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA) },
            onDismiss = { showRationale = false }
        )
    }

    content(hasPermission.value)
}

@Composable
private fun PermissionRationaleDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Camera Access Required") },
        text = { Text("This feature needs camera access to work properly") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Try Again")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}