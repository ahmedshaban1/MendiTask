package com.mendi.task.screens.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendi.task.core.domain.onError
import com.mendi.task.core.domain.onSuccess
import com.mendi.task.core.presentation.toUiText
import com.mendi.task.screens.settings.domain.usecases.GetGamesSettingsUseCase
import com.mendi.task.screens.settings.domain.usecases.UpdateGameSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val getGamesSettingsUseCase: GetGamesSettingsUseCase,
  private val updateGameSettingUseCase: UpdateGameSettingUseCase,
) : ViewModel() {
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

        it.onError { error ->
          _state.update {
            it.copy(
              errorMessage = error.toUiText(),
            )
          }
        }
      }
    }
  }

  fun updateSettingItem(
    updateSettingState: UpdateSettingState,
  ) {
    val oldSettings = _state.value.settings
    viewModelScope.launch {
      updateGameSettingUseCase(
        oldSettings = oldSettings,
        targetGameIndex = updateSettingState.gameIndex,
        targetSettingsGroupIndex = updateSettingState.settingsGroupIndex,
        targetAggregatedSettingIndex = updateSettingState.aggregatedSettingIndex,
        targetSettingItemIndex = updateSettingState.settingItemIndex,
      ).collect {
        it.onError { error ->
          _state.update {
            it.copy(
              errorMessage = error.toUiText(),
            )
          }
        }
      }
    }
  }

  // this is recommended way to remove message by google
  // we can use SharedFlow or channel instead but it might skip some events
  fun removeErrorMessage() {
    _state.update {
      it.copy(errorMessage = null)
    }
  }
}
