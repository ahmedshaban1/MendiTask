package com.mendi.task.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
  @Serializable
  data object HomeScreen : Screen

  @Serializable
  data object SettingsScreen : Screen
}
