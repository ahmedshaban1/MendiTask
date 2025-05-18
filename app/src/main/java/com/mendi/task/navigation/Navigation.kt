package com.mendi.task.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mendi.task.screens.session.presentation.SessionsScreen

@Composable
fun SetUpNavigation(
  navHostController: NavHostController,
) {
  NavHost(navController = navHostController, startDestination = Screen.HomeScreen) {
    composable<Screen.HomeScreen> {
      SessionsScreen()
    }
    composable<Screen.SettingsScreen> {
      Text("Settings")
    }
  }
}
