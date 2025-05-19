package com.mendi.task.screens.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mendi.task.R
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.smallShape
import com.mendi.task.ui.theme.spacing

@Composable
fun NeuralActivity(
  modifier: Modifier = Modifier,
) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.TopCenter,
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_trending),
      modifier = Modifier.size(12.dp),
      contentDescription = "NeuralActivity",
      tint = Color.White.copy(alpha = .7f),
    )
    Column(
      Modifier
        .padding(top = 8.dp)
        .width(58.dp)
        .clip(smallShape)
        .background(color = Color.White.copy(alpha = .1f)),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = "Neural Activity",
        color = Color.White,
        fontSize = 6.sp,
        lineHeight = 15.sp,
      )

      Text(
        modifier = Modifier.padding(bottom = MaterialTheme.spacing.small),
        text = "19%",
        color = Color.White,
        fontSize = 10.sp,
        lineHeight = 10.sp,
      )
    }
  }
}

@PreviewLightDark
@Composable
@Preview
fun NeuralActivityPreview() {
  MendiTaskTheme {
    NeuralActivity()
  }
}
