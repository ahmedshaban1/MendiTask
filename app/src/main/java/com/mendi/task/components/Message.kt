package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.ui.theme.MendiTaskTheme

@Composable
fun Message(
  title: String,
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      text = title,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.labelLarge,
    )
  }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun NoResultsPreview() {
  MendiTaskTheme {
    Message("No Results")
  }
}
