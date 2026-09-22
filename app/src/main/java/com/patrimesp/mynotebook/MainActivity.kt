package com.patrimesp.mynotebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.patrimesp.mynotebook.core.navigation.NavigationWrapper
import com.patrimesp.mynotebook.ui.theme.MyNotebookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotebookTheme {
                NavigationWrapper()
            }
        }
    }
}
