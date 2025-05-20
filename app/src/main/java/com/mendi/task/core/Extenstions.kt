package com.mendi.task.core

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Long.toTimestamp(): LocalDateTime {
  return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.toLong(): Long {
  return toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun LocalDateTime.isToday(): Boolean {
  val today = Clock.System.now()
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .date

  return this.date == today
}

fun LocalDateTime.displayDate(): String {
  return if (isToday()) {
    this.time.toString().substring(0, 5)
  } else {
    val javaDateTime = java.time.LocalDateTime.of(this.year, this.monthNumber, this.dayOfMonth, this.hour, this.minute)
    val formatter = DateTimeFormatter.ofPattern("d MMM HH:mm", Locale.getDefault())
    javaDateTime.format(formatter)
  }
}
