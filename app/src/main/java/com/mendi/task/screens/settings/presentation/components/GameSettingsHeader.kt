package com.mendi.task.screens.settings.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing
import com.mendi.task.ui.theme.xLargeShape

@Composable
fun GameSettingsHeader(
  modifier: Modifier = Modifier,
  coverRes: Int,
  gameName: String,
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(230.dp)
      .clip(xLargeShape),
  ) {
    Image(
      modifier = Modifier.fillMaxSize(),
      painter = painterResource(coverRes),
      contentDescription = gameName,
      contentScale = ContentScale.Crop,
    )
    Column(
      Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Row(
        Modifier
          .fillMaxWidth()
          .padding(top = MaterialTheme.spacing.large)
          .padding(horizontal = MaterialTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        LinearProgressIndicator(
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
          progress = { .35f },
          gapSize = 0.dp,
          strokeCap = StrokeCap.Butt,
          color = Color.White,
          trackColor = Color.White.copy(alpha = .1f),
          drawStopIndicator = {},
        )
        Spacer(Modifier.width(MaterialTheme.spacing.large))
        Icon(
          painter = painterResource(R.drawable.ic_close),
          contentDescription = stringResource(R.string.close),
          tint = Color.White.copy(alpha = .7f),
        )
      }
      ActivityInfo()
    }
    Box(
      modifier = Modifier.align(Alignment.BottomEnd)
        .padding(MaterialTheme.spacing.large),
    ) {
      NeuralActivity()
    }
  }
}

@PreviewLightDark
@Preview
@Composable
fun GameSettingsHeaderPreview() {
  MendiTaskTheme {
    GameSettingsHeader(
      coverRes = R.drawable.space_hills,
      gameName = "Space Quest",
    )
  }
}
