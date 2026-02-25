package com.aura.gamifiedlifeos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aura.gamifiedlifeos.ui.dashboard.DashboardScreen
import com.aura.gamifiedlifeos.ui.theme.GamifiedLifeOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifiedLifeOSTheme {
                DashboardScreen()
            }
        }
    }
}
