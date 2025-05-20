package com.mendi.task.screens.settings.data.mappers

import com.mendi.task.screens.settings.data.entities.AggregatedSettingsEntity
import com.mendi.task.screens.settings.data.entities.GameEntity
import com.mendi.task.screens.settings.data.entities.SettingsGroupEntity
import com.mendi.task.screens.settings.data.entities.SettingsItemEntity
import com.mendi.task.screens.settings.data.entities.SettingsTypeEntity
import com.mendi.task.screens.settings.domain.AggregatedSettings
import com.mendi.task.screens.settings.domain.Game
import com.mendi.task.screens.settings.domain.SettingsGroup
import com.mendi.task.screens.settings.domain.SettingsItem
import com.mendi.task.screens.settings.domain.SettingsType

// Game Entity to game
fun GameEntity.toGame(
  id: String,
): Game {
  return Game(
    id = id,
    order = order,
    name = name,
    settingsGroups = settingsGroups.map { it.toSettingsGroup() },
  )
}

fun SettingsGroupEntity.toSettingsGroup(): SettingsGroup {
  return SettingsGroup(
    name = name,
    aggregatedSettings = aggregatedSettings.map { it.toAggregatedSettings() },
  )
}

fun AggregatedSettingsEntity.toAggregatedSettings(): AggregatedSettings {
  return AggregatedSettings(
    multiSelect = multiSelect,
    settings = settings.map { it.toSettingsItem() },
  )
}

fun SettingsItemEntity.toSettingsItem(): SettingsItem {
  return SettingsItem(
    label = label,
    selected = selected,
    type = type.toSettingsType(),
  )
}

fun SettingsTypeEntity.toSettingsType(): SettingsType {
  return when (this) {
    SettingsTypeEntity.SELECT -> SettingsType.SELECT
    SettingsTypeEntity.CHECK -> SettingsType.CHECK
  }
}

// Game to Game Entity

fun Game.toGameEntity(): GameEntity {
  return GameEntity(
    order = order,
    name = name,
    settingsGroups = settingsGroups.map { it.toSettingsGroup() },
  )
}

fun SettingsGroup.toSettingsGroup(): SettingsGroupEntity {
  return SettingsGroupEntity(
    name = name,
    aggregatedSettings = aggregatedSettings.map { it.toAggregatedSettings() },
  )
}

fun AggregatedSettings.toAggregatedSettings(): AggregatedSettingsEntity {
  return AggregatedSettingsEntity(
    multiSelect = multiSelect,
    settings = settings.map { it.toSettingsItem() },
  )
}

fun SettingsItem.toSettingsItem(): SettingsItemEntity {
  return SettingsItemEntity(
    label = label,
    selected = selected,
    type = type.toSettingsType(),
  )
}

fun SettingsType.toSettingsType(): SettingsTypeEntity {
  return when (this) {
    SettingsType.SELECT -> SettingsTypeEntity.SELECT
    SettingsType.CHECK -> SettingsTypeEntity.CHECK
  }
}
