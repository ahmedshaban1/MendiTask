package com.mendi.task.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavigation(
  navHostController: NavHostController,
) {
  NavHost(navController = navHostController, startDestination = Screen.HomeScreen) {
    composable<Screen.HomeScreen> {
      Text("Home")
    }
    composable<Screen.SettingsScreen> {
      Text("Settings")
    }
  }
}
