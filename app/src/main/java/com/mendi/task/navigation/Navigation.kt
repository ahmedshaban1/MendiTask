package com.mendi.task.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mendi.task.screens.session.presentation.SessionsScreen
import com.mendi.task.screens.settings.presentation.SettingsScreen

@Composable
fun SetUpNavigation(
  navHostController: NavHostController,
) {
  NavHost(navController = navHostController, startDestination = Screen.HomeScreen) {
    composable<Screen.HomeScreen> {
      SessionsScreen(
        onNavigate = {
          navHostController.navigate(Screen.SettingsScreen)
        },
      )
    }
    composable<Screen.SettingsScreen> {
      SettingsScreen()
    }
  }
}
