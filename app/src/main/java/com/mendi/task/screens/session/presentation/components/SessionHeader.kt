package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.spacing

@Composable
fun SessionHeader(
  time: String,
  duration: String,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = time,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.labelLarge,
    )
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        painter = painterResource(R.drawable.ic_timer),
        contentDescription = duration,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      Spacer(Modifier.height(MaterialTheme.spacing.small))
      Text(
        text = duration,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelLarge,
      )
    }
  }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun SessionHeaderPreview() {
  MendiTaskTheme {
    SessionHeader(time = "18:12", duration = "3 min")
  }
}
