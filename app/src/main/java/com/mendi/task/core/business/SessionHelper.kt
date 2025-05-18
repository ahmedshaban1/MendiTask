package com.mendi.task.core.business

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class SessionHelper @Inject constructor() {
  fun randomFrom(
    min: Int,
    max: Int,
  ) = (min..max).random()
  fun now() = Clock.System.now().toLocalDateTime()
  private fun Instant.toLocalDateTime(): LocalDateTime = toLocalDateTime(timeZone)
  private val timeZone get() = TimeZone.currentSystemDefault()
}
