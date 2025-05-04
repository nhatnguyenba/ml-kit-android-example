package com.nhatnguyenba.ml_kit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nhatnguyenba.ml_kit.presentation.barcode.BarcodeScreen
import com.nhatnguyenba.ml_kit.presentation.imagelabel.ImageLabelingScreen
import com.nhatnguyenba.ml_kit.presentation.main.MainScreen
import com.nhatnguyenba.ml_kit.presentation.translation.TranslationScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("barcode") { BarcodeScreen() }
        composable("labeling") { ImageLabelingScreen() }
        composable("translation") { TranslationScreen() }
    }
}