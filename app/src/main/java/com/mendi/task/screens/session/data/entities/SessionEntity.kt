package com.mendi.task.screens.session.data.entities

data class SessionEntity(
  val timestamp: Long = 0L,
  val sessionDuration: Int = 0,
  val score: Int = 0,
  val activity: Int = 0,
  val bloodFlow: Int = 0,
  val focus: Int = 0,
)
