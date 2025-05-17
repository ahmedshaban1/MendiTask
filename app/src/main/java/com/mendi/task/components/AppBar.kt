package com.mendi.task.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
  modifier: Modifier = Modifier,
) {
  TopAppBar(
    modifier = modifier,
    title = {
      Row {
        Icon(
          painter = painterResource(R.drawable.logo),
          contentDescription = "Logo",
          tint = MaterialTheme.colorScheme.onSurface,
        )
      }
    },
  )
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun AppBarPreview() {
  MendiTaskTheme {
    AppBar()
  }
}
