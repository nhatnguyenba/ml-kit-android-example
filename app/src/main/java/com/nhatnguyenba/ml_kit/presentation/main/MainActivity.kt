package com.nhatnguyenba.ml_kit.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nhatnguyenba.ml_kit.presentation.navigation.AppNavigation
import com.nhatnguyenba.ml_kit.presentation.theme.MLKitAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MLKitAppTheme {
//                val view = LocalView.current
//                val window = (view.context as Activity).window

//                // Set status/navigation bar colors
//                SideEffect {
//                    WindowCompat.getInsetsController(window, view).apply {
//                        isAppearanceLightStatusBars = !isSystemInDarkTheme()
//                        isAppearanceLightNavigationBars = !isSystemInDarkTheme()
//                    }
//                    window.statusBarColor = Color.Transparent.toArgb()
//                    window.navigationBarColor = Color.Transparent.toArgb()
//                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}