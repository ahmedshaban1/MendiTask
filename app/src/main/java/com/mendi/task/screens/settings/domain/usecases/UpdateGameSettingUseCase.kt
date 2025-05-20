package com.mendi.task.screens.settings.domain.usecases

import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.settings.domain.Game
import com.mendi.task.screens.settings.domain.GameSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateGameSettingUseCase @Inject constructor(
  private val repository: GameSettingsRepository,
) {
  suspend operator fun invoke(
    oldSettings: List<Game>,
    targetGameIndex: Int,
    targetSettingsGroupIndex: Int,
    targetAggregatedSettingIndex: Int,
    targetSettingItemIndex: Int,
  ): Flow<Result<Any, DataError.Remote>> {
    val updatedGames = oldSettings.mapIndexed { gameIndex, game ->
      if (gameIndex != targetGameIndex) return@mapIndexed game

      val updatedSettingsGroups = game.settingsGroups.mapIndexed { groupIndex, group ->
        if (groupIndex != targetSettingsGroupIndex) return@mapIndexed group

        val updatedAggregatedSettings = group.aggregatedSettings.mapIndexed { aggIndex, agg ->
          if (aggIndex != targetAggregatedSettingIndex) return@mapIndexed agg
          val updatedSettings = agg.settings.mapIndexed { settingIndex, setting ->
            if (settingIndex == targetSettingItemIndex) {
              setting.copy(selected = !setting.selected)
            } else {
              if (agg.multiSelect) {
                setting
              } else {
                setting.copy(selected = false)
              }
            }
          }

          agg.copy(settings = updatedSettings)
        }

        group.copy(aggregatedSettings = updatedAggregatedSettings)
      }

      game.copy(settingsGroups = updatedSettingsGroups)
    }

    val updatedGame = updatedGames[targetGameIndex]
    return repository.updateGameSettings(updatedGame)
  }
}
