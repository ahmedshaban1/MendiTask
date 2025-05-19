package com.mendi.task.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.mendi.task.ui.theme.MendiTaskTheme

@Composable
fun TertiarySwitch(
  checked: Boolean,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  onCheckedChange: (Boolean) -> Unit,
) {
  Switch(
    modifier = modifier,
    checked = checked,
    enabled = enabled,
    onCheckedChange = onCheckedChange,
    colors = SwitchDefaults.colors(
      uncheckedBorderColor = MaterialTheme.colorScheme.outline,
      uncheckedTrackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
      uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant,

      checkedTrackColor = MaterialTheme.colorScheme.tertiary,
      checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
    ),
  )
}

@PreviewLightDark
@Composable
private fun PreviewTertiarySwitch() {
  MendiTaskTheme {
    TertiarySwitch(checked = true) {}
    TertiarySwitch(checked = false) {}
    TertiarySwitch(enabled = false, checked = true) {}
    TertiarySwitch(enabled = false, checked = false) {}
  }
}
