package com.mendi.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
      MendiTaskTheme {
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        ) { innerPadding ->
          Box(
            modifier = Modifier
              .padding(innerPadding)
              .fillMaxSize(),
          ) {
          }
        }
      }
    }
  }
}
