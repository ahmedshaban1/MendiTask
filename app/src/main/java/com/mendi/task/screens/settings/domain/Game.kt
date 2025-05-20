package com.mendi.task.screens.settings.domain

import kotlinx.serialization.Serializable

@Serializable
data class Game(
  val name: String = "",
  val id: String? = null,
  val settingsGroups: List<SettingsGroup> = emptyList(),
  val order: Int = 0,
)

@Serializable
data class SettingsGroup(
  val name: String = "",
  val aggregatedSettings: List<AggregatedSettings> = emptyList(),
)

@Serializable
data class AggregatedSettings(
  val multiSelect: Boolean = false,
  val settings: List<SettingsItem> = emptyList(),
)

@Serializable
enum class SettingsType {
  SELECT,
  CHECK,
}

@Serializable
data class SettingsItem(
  val type: SettingsType = SettingsType.SELECT,
  val label: String = "",
  var selected: Boolean = false,
)
