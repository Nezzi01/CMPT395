package com.cmpt395.deferralapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmpt395.deferralapp.ui.theme.SettingsTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SettingsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsList(modifier: Modifier = Modifier)
{
    var darkModeEnabled by remember { mutableStateOf(false) }
    var screenReaderEnabled by remember { mutableStateOf(false) }
    var textScale by remember { mutableFloatStateOf(0.5F) }
    val textScalePercent: Int = (50 + 100 * textScale).roundToInt()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //Invoke Nav Bar here...

        //Dark Mode toggle
        SettingsToggle(
            labelID = R.string.settings_dark_mode,
            value = darkModeEnabled,
            onToggle  = {darkModeEnabled = it}
        )
        HorizontalDivider(thickness = 2.dp)

        //Screen Reader toggle
        SettingsToggle(
            labelID = R.string.settings_screen_reader,
            value = screenReaderEnabled,
            onToggle = {screenReaderEnabled = it}
        )
        HorizontalDivider(thickness = 2.dp)

        //Text Scale slider
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.settings_font_size), fontSize = 24.sp)
                Text("$textScalePercent%", fontSize = 24.sp)
            }
            Slider(value = textScale, onValueChange = {textScale = it})
        }
        HorizontalDivider(thickness = 2.dp)
    }
}

/**
 * Creates a Material 3 Switch inline with a text label given its string resource.
 */
@Composable
fun SettingsToggle(
    labelID: Int,
    value: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(stringResource(labelID), fontSize = 24.sp)
        Switch(checked = value, onCheckedChange = onToggle)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SettingsTheme {
        SettingsList()
    }
}