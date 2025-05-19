package com.mendi.task.screens.session.presentation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mendi.task.R
import com.mendi.task.components.AppBar
import com.mendi.task.components.Loading
import com.mendi.task.screens.session.presentation.components.SessionDetails
import com.mendi.task.screens.session.presentation.components.SessionInfo
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing

@Composable
fun SessionsScreen(
  modifier: Modifier = Modifier,
  onNavigate: () -> Unit,
  viewModel: SessionsViewModel = hiltViewModel(),
) {
  val state by viewModel.state.collectAsStateWithLifecycle()
  SessionsContent(
    modifier = modifier,
    state = state,
    onNavigate = onNavigate,
    onCreateSession = {
      viewModel.createSession()
    },
  )
}

@Composable
fun SessionsContent(
  modifier: Modifier = Modifier,
  state: SessionsState,
  onCreateSession: () -> Unit,
  onNavigate: () -> Unit,
) {
  Scaffold(
    topBar = { AppBar(onNavigate = onNavigate) },
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.surface),
  ) { paddingValues ->
    AnimatedVisibility(state.isLoading) {
      Loading(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues),
        title = stringResource(R.string.loading),
      )
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          paddingValues,
        )
        .padding(horizontal = MaterialTheme.spacing.large),
      verticalArrangement = Arrangement.SpaceBetween,
    ) {
      LazyColumn(
        Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
      ) {
        state.latestSession?.let {
          item {
            Text(text = stringResource(R.string.latest_results))
          }
          item {
            SessionInfo(session = it) {
              SessionDetails(session = it)
            }
          }
        }
        if (state.sessions.isNotEmpty()) {
          item {
            Text(text = stringResource(R.string.session_history))
          }
          items(state.sessions) { session ->
            SessionInfo(session = session) {}
          }
        }
      }
      Button(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp),
        onClick = onCreateSession,
      ) {
        Text(
          text = stringResource(R.string.create_session),
          style = MaterialTheme.typography.labelLarge,
        )
      }
    }
  }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun ScorePreview() {
  MendiTaskTheme {
    SessionsContent(
      state = SessionsState(),
      onNavigate = {},
      onCreateSession = {},
    )
  }
}
