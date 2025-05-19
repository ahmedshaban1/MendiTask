package com.mendi.task.screens.settings.domain

import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface GameSettingsRepository {
  suspend fun getGamesSettings(): Flow<Result<List<Game>, DataError.Remote>>
}
