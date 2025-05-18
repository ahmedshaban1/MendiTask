package com.mendi.task.screens.session.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mendi.task.R
import com.mendi.task.components.StatusInfo
import com.mendi.task.screens.session.domain.Session
import com.mendi.task.ui.theme.spacing

@Composable
fun SessionDetails(
  modifier: Modifier = Modifier,
  session: Session,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
  ) {
    StatusInfo(
      modifier = Modifier.weight(1f),
      iconRes = R.drawable.ic_brain,
      label = stringResource(R.string.activity),
      result = "+${session.activity}%",
    )
    StatusInfo(
      modifier = Modifier.weight(1f),
      iconRes = R.drawable.ic_blood,
      label = stringResource(R.string.blood_flow),
      result = "${session.bloodFlow}P",
    )
    StatusInfo(
      modifier = Modifier.weight(1f),
      iconRes = R.drawable.ic_focus,
      label = stringResource(R.string.focus),
      result = "${session.focus}s",
    )
  }
  Spacer(Modifier.height(MaterialTheme.spacing.small))
}
