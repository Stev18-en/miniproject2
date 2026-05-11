package com.stevenmarchy0013.simukmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stevenmarchy0013.simukmin.screen.MainScreen
import com.stevenmarchy0013.simukmin.ui.theme.SiMukminTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            SiMukminTheme {

                MainScreen()
            }
        }
    }
}