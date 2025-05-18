package com.mendi.task.screens.session.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendi.task.core.domain.onError
import com.mendi.task.core.domain.onSuccess
import com.mendi.task.core.presentation.toUiText
import com.mendi.task.screens.session.domain.usecases.CreateSessionUseCase
import com.mendi.task.screens.session.domain.usecases.GetSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
  private val createSessionUseCase: CreateSessionUseCase,
  private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
  private val _state = MutableStateFlow(SessionsState())
  val state = _state.asStateFlow()

  init {
    getSessions()
  }

  private fun getSessions() {
    viewModelScope.launch {
      _state.update {
        it.copy(
          isLoading = true,
        )
      }
      getSessionsUseCase().collect {
        it.onSuccess { results ->
          _state.update { current ->
            current.copy(
              isLoading = false,
              sessions = results,
            )
          }
        }
        it.onError { results ->
          _state.update { current ->
            current.copy(
              isLoading = false,
              errorMessage = results.toUiText(),
            )
          }
        }
      }
    }
  }

  fun createSession() {
    viewModelScope.launch {
      _state.update { current ->
        current.copy(isLoading = true)
      }
      createSessionUseCase().collect { results ->
        results.onError {
          _state.update { current ->
            current.copy(
              isLoading = false,
              errorMessage = it.toUiText(),
            )
          }
        }
        results.onSuccess {
          _state.update { current ->
            current.copy(
              isLoading = false,
            )
          }
        }
      }
    }
  }
}
