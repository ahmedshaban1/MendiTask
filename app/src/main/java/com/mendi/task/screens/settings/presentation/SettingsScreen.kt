package com.mendi.task.screens.settings.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mendi.task.R
import com.mendi.task.screens.settings.domain.Game
import com.mendi.task.screens.settings.domain.SettingsType
import com.mendi.task.screens.settings.presentation.components.GameName
import com.mendi.task.screens.settings.presentation.components.GameSettingsHeader
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
  modifier: Modifier = Modifier,
  viewModel: SettingsViewModel = hiltViewModel(),
) {
  val state by viewModel.state.collectAsStateWithLifecycle()
  val context = LocalContext.current

  Scaffold(
    modifier = Modifier,
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(
            text = stringResource(R.string.settings_title),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
          )
        },
      )
    },
  ) { padding ->
    Box(
      modifier = modifier
        .background(color = MaterialTheme.colorScheme.surface)
        .padding(padding)
        .padding(horizontal = MaterialTheme.spacing.large)
        .fillMaxSize(),
    ) {
      state.errorMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        viewModel.removeErrorMessage()
      }
      SettingsContent(
        state = state,
        onUpdateSetting = {
          viewModel.updateSettingItem(it)
        },
      )
    }
  }
}

@Composable
fun SettingsContent(
  modifier: Modifier = Modifier,
  state: GamesSettingsState,
  onUpdateSetting: (updateSettingState: UpdateSettingState) -> Unit,
) {
  var currentPage by remember { mutableIntStateOf(0) }
  val scope = rememberCoroutineScope()
  val pagerState = rememberPagerState(
    initialPage = currentPage,
    pageCount = { state.settings.size },
  )
  HorizontalPager(
    modifier = modifier.fillMaxSize(),
    userScrollEnabled = false,
    state = pagerState,
  ) { page ->
    GameSettingsContent(
      game = state.settings[page],
      cover = state.covers[page],
      onPreviousGame = {
        if (pagerState.canScrollBackward) {
          scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
          }
        }
      },
      onNextGame = {
        if (pagerState.canScrollForward) {
          scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
          }
        }
      },

      onUpdateSetting = { updateSettingState ->
        onUpdateSetting(updateSettingState.copy(gameIndex = page))
      },
    )
  }
}

@Composable
fun GameSettingsContent(
  modifier: Modifier = Modifier,
  game: Game,
  cover: Int,
  onNextGame: () -> Unit,
  onPreviousGame: () -> Unit,
  onUpdateSetting: (updateSettingState: UpdateSettingState) -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
  ) {
    GameSettingsHeader(
      coverRes = cover,
      gameName = game.name,
    )
    GameName(
      gameName = game.name,
      onNextGame = onNextGame,
      onPreviousGame = onPreviousGame,
    )
    game.settingsGroups.forEachIndexed { settingsGroupIndex, settingsGroup ->
      Text(
        text = settingsGroup.name,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      settingsGroup.aggregatedSettings.forEachIndexed { aggregatedSettingIndex, aggregatedSetting ->
        SettingsCard(
          settings = aggregatedSetting.settings.mapIndexed { settingItemIndex, settingItem ->
            when (settingItem.type) {
              SettingsType.SELECT -> SettingsCard.Select(
                label = settingItem.label,
                checked = settingItem.selected,
                onClick = {
                  onUpdateSetting(
                    UpdateSettingState(
                      settingsGroupIndex = settingsGroupIndex,
                      aggregatedSettingIndex = aggregatedSettingIndex,
                      settingItemIndex = settingItemIndex,
                    ),
                  )
                },
              )

              SettingsType.CHECK -> SettingsCard.Check(
                label = settingItem.label,
                checked = settingItem.selected,
                onChecked = {
                  onUpdateSetting(
                    UpdateSettingState(
                      settingsGroupIndex = settingsGroupIndex,
                      aggregatedSettingIndex = aggregatedSettingIndex,
                      settingItemIndex = settingItemIndex,
                    ),
                  )
                },
              )
            }
          },
        )
      }
    }
  }
}

@PreviewLightDark
@Composable
private fun SettingsScreenPreview() {
  MendiTaskTheme {
    SettingsScreen()
  }
}
