package com.stevenmarchy0013.simukmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme
import com.stevenmarchy0013.simukmin.navigation.SetupNavGraph
import com.stevenmarchy0013.simukmin.util.SettingsDataStore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = SettingsDataStore(this)

        setContent {
            val themeColor by dataStore.themeFlow.collectAsState(initial = 0)

            SiMukminTheme(themeColor = themeColor) {
                SetupNavGraph()
            }
        }
    }
}