package com.mendi.task.screens.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing

@Composable
fun GameName(
  modifier: Modifier = Modifier,
  gameName: String,
  onNextGame: () -> Unit,
  onPreviousGame: () -> Unit,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = MaterialTheme.spacing.medium),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    IconButton(
      onClick = onPreviousGame,
    ) {
      Icon(
        painter = painterResource(R.drawable.ic_back),
        contentDescription = "back",
        tint = MaterialTheme.colorScheme.primary,
      )
    }

    Text(
      text = gameName,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
    )
    IconButton(onClick = onNextGame) {
      Icon(
        painter = painterResource(R.drawable.ic_forward),
        contentDescription = "forward",
        tint = MaterialTheme.colorScheme.primary,
      )
    }
  }
}

@PreviewLightDark
@Preview
@Composable
fun GameNamePreview() {
  MendiTaskTheme {
    GameName(
      gameName = "SpaceZ",
      onNextGame = {},
      onPreviousGame = {},
    )
  }
}
