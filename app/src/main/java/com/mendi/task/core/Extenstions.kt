package com.mendi.task.core

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun Long.toTimestamp(): LocalDateTime {
  return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.toLong(): Long {
  return toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}
