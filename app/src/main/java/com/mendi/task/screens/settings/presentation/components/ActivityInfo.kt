package com.mendi.task.screens.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.dmFontFamily
import com.mendi.task.ui.theme.spacing

@Composable
fun ActivityInfo(
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xLarge),
  ) {
    ActivityInfoItem(
      iconRes = R.drawable.ic_water,
      value = "23%",
    )
    ActivityInfoItem(
      iconRes = R.drawable.ic_trending,
      value = "17s",
    )
    ActivityInfoItem(
      iconRes = R.drawable.ic_star,
      value = "697",
    )
  }
}

@Composable
fun ActivityInfoItem(
  modifier: Modifier = Modifier,
  iconRes: Int,
  value: String,
) {
  Row(
    modifier,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      painter = painterResource(iconRes),
      contentDescription = value,
      tint = Color.White.copy(alpha = .7f),
    )
    Text(
      text = value,
      color = Color.White.copy(alpha = .7f),
      fontFamily = dmFontFamily,
      fontWeight = FontWeight.W500,
      fontSize = 10.sp,
    )
  }
}

@PreviewLightDark
@Composable
@Preview
fun ActivityInfoPreview() {
  MendiTaskTheme {
    Column {
      ActivityInfoItem(
        iconRes = R.drawable.ic_water,
        value = "23%",
      )
      ActivityInfo()
    }
  }
}
