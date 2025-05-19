package com.mendi.task.screens.settings.domain.usecases

import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.settings.domain.Game
import com.mendi.task.screens.settings.domain.GameSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesSettingsUseCase @Inject constructor(
  private val repository: GameSettingsRepository,
) {
  suspend operator fun invoke(): Flow<Result<List<Game>, DataError.Remote>> {
    return repository.getGamesSettings()
  }
}
