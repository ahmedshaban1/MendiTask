package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.mediumShape
import com.mendi.task.ui.theme.spacing

@Composable
fun StatusInfo(
  iconRes: Int,
  label: String,
  result: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .clip(mediumShape)
      .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
      .padding(MaterialTheme.spacing.medium),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Icon(
      painter = painterResource(iconRes),
      contentDescription = label,
      tint = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    Text(
      text = label,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      style = MaterialTheme.typography.labelSmall,
    )
    Text(
      text = result,
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
    StatusInfo(iconRes = R.drawable.ic_focus, label = "Foucs", result = "18s")
  }
}
