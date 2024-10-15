package com.rhyttah.schwebsite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rhyttah.schwebsite.navigation.AppNavHost
import com.rhyttah.schwebsite.ui.theme.SchwebsiteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchwebsiteTheme {
                AppNavHost()
                }
            }
        }
    }

