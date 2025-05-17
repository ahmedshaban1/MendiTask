package com.mendi.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.mendi.task.navigation.SetUpNavigation
import com.mendi.task.ui.theme.MendiTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(
    savedInstanceState: Bundle?,
  ) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val navController = rememberNavController()
      MendiTaskTheme {
        SetUpNavigation(navController)
      }
    }
  }
}
