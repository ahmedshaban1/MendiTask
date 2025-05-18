package com.mendi.task.screens.session.presentation

import com.mendi.task.core.presentation.UiText
import com.mendi.task.screens.session.domain.Session

data class SessionsState(
  val isLoading: Boolean = false,
  val errorMessage: UiText? = null,
  val sessions: List<Session> = emptyList(),
)
