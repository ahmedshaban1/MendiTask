package com.mendi.task.screens.session.domain.usecases

import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.session.domain.Session
import com.mendi.task.screens.session.domain.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionsUseCase @Inject constructor(
  private val repository: SessionRepository,
) {
  suspend operator fun invoke(): Flow<Result<List<Session>, DataError.Remote>> {
    return repository.getSessions()
  }
}
