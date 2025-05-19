package com.mendi.task.screens.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendi.task.core.domain.onSuccess
import com.mendi.task.screens.settings.domain.usecases.GetGamesSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getGamesSettingsUseCase: GetGamesSettingsUseCase) :
  ViewModel() {
  private val _state = MutableStateFlow<GamesSettingsState>(GamesSettingsState())
  val state = _state.asStateFlow()

  init {
    getGamesSettings()
  }

  private fun getGamesSettings() {
    viewModelScope.launch {
      getGamesSettingsUseCase().collect {
        it.onSuccess { results ->
          _state.update {
            it.copy(
              settings = results,
            )
          }
        }
      }
    }
  }
}
