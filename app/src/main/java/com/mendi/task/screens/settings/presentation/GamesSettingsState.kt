package com.mendi.task.screens.settings.presentation

import com.mendi.task.R
import com.mendi.task.screens.settings.domain.Game

data class GamesSettingsState(
  val settings: List<Game> = emptyList(),
  val covers: List<Int> = listOf(
    R.drawable.space_hills,
    R.drawable.flower_hills,
    R.drawable.rolling_hills,
  ),
  val errorMessage: Int? = null,
)

data class UpdateSettingState(
  val gameIndex: Int = 0,
  val settingsGroupIndex: Int,
  val aggregatedSettingIndex: Int,
  val settingItemIndex: Int,
)
