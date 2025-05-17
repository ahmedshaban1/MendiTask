package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing

@Composable
fun Loading(
  title: String,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CircularProgressIndicator(
      color = MaterialTheme.colorScheme.tertiary,
    )
    Spacer(Modifier.height(MaterialTheme.spacing.small))
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
private fun LoadingPreview() {
  MendiTaskTheme {
    Loading("Loading")
  }
}
