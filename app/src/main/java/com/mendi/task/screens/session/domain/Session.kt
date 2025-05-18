package com.mendi.task.screens.session.domain

import kotlinx.datetime.LocalDateTime

data class Session(
  val timestamp: LocalDateTime,
  val sessionDuration: Int,
  val score: Int,
  val activity: Int,
  val bloodFlow: Int,
  val focus: Int,
)
