package com.mendi.task.screens.settings.data.entities

import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable

@Serializable
data class GameEntity(
  @get:Exclude @set:Exclude
  var id: String? = null,
  val name: String = "",
  val settingsGroups: List<SettingsGroupEntity> = emptyList(),
  val order: Int = 0,
)

@Serializable
data class SettingsGroupEntity(
  val name: String = "",
  val aggregatedSettings: List<AggregatedSettingsEntity> = emptyList(),
)

@Serializable
data class AggregatedSettingsEntity(
  val multiSelect: Boolean = false,
  val settings: List<SettingsItemEntity> = emptyList(),
)

@Serializable
enum class SettingsTypeEntity {
  SELECT,
  CHECK,
}

@Serializable
data class SettingsItemEntity(
  val type: SettingsTypeEntity = SettingsTypeEntity.SELECT,
  val label: String = "",
  val selected: Boolean = false,
)

val games: List<GameEntity> = listOf(
  GameEntity(name = "Space Quest", settingsGroups = getGroups("Asteroid"), order = 1),
  GameEntity(name = "Flower Power", settingsGroups = getGroups("Flower"), order = 2),
  GameEntity(name = "Rolling Hills", settingsGroups = getGroups("Mendi Logo"), order = 3),
)

fun getGroups(
  targetGame: String,
): List<SettingsGroupEntity> {
  return listOf(
    SettingsGroupEntity(
      name = "Avatar",
      aggregatedSettings = listOf(
        AggregatedSettingsEntity(
          multiSelect = false,
          settings = listOf(
            SettingsItemEntity(label = targetGame, selected = false, type = SettingsTypeEntity.SELECT),
            SettingsItemEntity(label = "Circle", selected = false, type = SettingsTypeEntity.SELECT),
          ),
        ),
        AggregatedSettingsEntity(
          multiSelect = true,
          settings = listOf(
            SettingsItemEntity(label = "Glow effect", selected = false, type = SettingsTypeEntity.CHECK),
            SettingsItemEntity(label = "Avatar spin", selected = false, type = SettingsTypeEntity.CHECK),
          ),
        ),
      ),
    ),
    SettingsGroupEntity(
      name = "Immersion settings",
      aggregatedSettings = listOf(
        AggregatedSettingsEntity(
          multiSelect = true,
          settings = listOf(
            SettingsItemEntity(label = "Vignette", selected = false, type = SettingsTypeEntity.CHECK),
            SettingsItemEntity(label = "In-game metrics", selected = false, type = SettingsTypeEntity.CHECK),
          ),
        ),
      ),
    ),
  )
}
