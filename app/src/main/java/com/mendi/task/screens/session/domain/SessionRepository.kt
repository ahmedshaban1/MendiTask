package com.mendi.task.screens.session.domain

import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
  suspend fun createSession(
    session: Session,
  ): Flow<Result<Unit, DataError.Remote>>
  suspend fun getSessions(): Flow<Result<List<Session>, DataError.Remote>>
}
