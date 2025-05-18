package com.mendi.task.screens.session.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.mendi.task.R
import com.mendi.task.components.Score
import com.mendi.task.components.SessionHeader
import com.mendi.task.components.StatusInfo
import com.mendi.task.screens.session.domain.Session
import com.mendi.task.ui.theme.MendiTaskTheme
import com.mendi.task.ui.theme.largeShape
import com.mendi.task.ui.theme.spacing
import kotlinx.datetime.LocalDateTime

@Composable
fun SessionInfo(
  modifier: Modifier = Modifier,
  session: Session,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .clip(largeShape)
      .background(color = MaterialTheme.colorScheme.surfaceContainer)
      .padding(MaterialTheme.spacing.xLarge),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    SessionHeader(time = "18:12", duration = "3 min")
    Spacer(Modifier.height(MaterialTheme.spacing.medium))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
    ) {
      StatusInfo(
        modifier = Modifier.weight(1f),
        iconRes = R.drawable.ic_brain,
        label = "Activity",
        result = "+24%",
      )
      StatusInfo(
        modifier = Modifier.weight(1f),
        iconRes = R.drawable.ic_blood,
        label = "Blood Low",
        result = "1730P",
      )
      StatusInfo(
        modifier = Modifier.weight(1f),
        iconRes = R.drawable.ic_focus,
        label = "Focus",
        result = "18s",
      )
    }
    Spacer(Modifier.height(MaterialTheme.spacing.small))
    Score(
      label = "Score",
      score = session.score.toString(),
    )
  }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
private fun ScorePreview() {
  MendiTaskTheme {
    SessionInfo(
      session = Session(
        sessionDuration = 1,
        score = 1,
        timestamp = LocalDateTime.parse("11-11-1993"),
        focus = 1,
        bloodFlow = 1,
        activity = 1,
      ),
    )
  }
}
