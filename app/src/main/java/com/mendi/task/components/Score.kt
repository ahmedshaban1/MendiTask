package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.mediumShape
import com.mendi.task.ui.theme.spacing

@Composable
fun Score(
  label: String,
  score: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .clip(mediumShape)
      .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
      .padding(MaterialTheme.spacing.medium),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = label,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      style = MaterialTheme.typography.labelSmall,
    )
    Text(
      text = score,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.displayMedium,
    )
  }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun ScorePreview() {
  MendiTaskTheme {
    Score(label = "Score", score = "90")
  }
}
