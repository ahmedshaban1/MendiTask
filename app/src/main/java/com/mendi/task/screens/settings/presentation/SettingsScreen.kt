package com.mendi.task.screens.settings.presentation

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

  Scaffold(
    modifier = Modifier.background(MaterialTheme.colorScheme.surface),
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
        .padding(padding)
        .padding(horizontal = MaterialTheme.spacing.large)
        .fillMaxSize(),
    ) {
      SettingsContent(state = state)
    }
  }
}

@Composable
fun SettingsContent(
  modifier: Modifier = Modifier,
  state: GamesSettingsState,
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
    game.settingsGroups.forEach { settingsGroup ->
      Text(
        text = settingsGroup.name,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      settingsGroup.aggregatedSettings.forEach { aggregatedSetting ->
        SettingsCard(
          settings = aggregatedSetting.settings.map {
            when (it.type) {
              SettingsType.SELECT -> SettingsCard.Select(
                label = it.label,
                checked = it.selected,
                onClick = {},
              )

              SettingsType.CHECK -> SettingsCard.Check(
                label = it.label,
                checked = it.selected,
                onChecked = {},
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
