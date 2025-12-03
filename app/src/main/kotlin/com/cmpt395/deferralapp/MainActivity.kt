package com.cmpt395.deferralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.cmpt395.deferralapp.state.SettingsManager
import com.cmpt395.deferralapp.ui.AppRoot
import com.cmpt395.deferralapp.ui.theme.DeferralAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        settingsManager = SettingsManager(applicationContext)

        setContent {
            //Allows dark mode to be toggled on the fly and have the changes be reflected immediately.
            val darkModeEnabled = settingsManager.isDarkModeEnabled.collectAsState(initial = false).value

            DeferralAppTheme(darkTheme = darkModeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppRoot(settingsManager, darkModeEnabled)
                }
            }
        }
    }
}
