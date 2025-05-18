package com.mendi.task.screens.session.domain.usecases

import com.mendi.task.core.business.SessionHelper
import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.session.domain.Session
import com.mendi.task.screens.session.domain.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateSessionUseCase @Inject constructor(
  private val repository: SessionRepository,
  private val sessionHelper: SessionHelper,
) {
  suspend operator fun invoke(): Flow<Result<Unit, DataError.Remote>> {
    val session = Session(
      timestamp = sessionHelper.now(),
      sessionDuration = sessionHelper.randomFrom(1, 10),
      score = sessionHelper.randomFrom(30, 100),
      activity = sessionHelper.randomFrom(7, 55),
      bloodFlow = sessionHelper.randomFrom(1720, 1900),
      focus = sessionHelper.randomFrom(1, 25),
    )
    return repository.createSession(session)
  }
}
