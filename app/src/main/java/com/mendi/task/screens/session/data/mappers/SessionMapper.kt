package com.mendi.task.screens.session.data.mappers

import com.mendi.task.core.toLong
import com.mendi.task.core.toTimestamp
import com.mendi.task.screens.session.data.entities.SessionEntity
import com.mendi.task.screens.session.domain.Session

fun Session.toSessionEntity(): SessionEntity {
  return SessionEntity(
    timestamp = this.timestamp.toLong(),
    sessionDuration = this.sessionDuration,
    score = this.score,
    activity = this.score,
    bloodFlow = this.bloodFlow,
    focus = this.focus,
  )
}

fun SessionEntity.toSession(): Session {
  return Session(
    timestamp = this.timestamp.toTimestamp(),
    sessionDuration = this.sessionDuration,
    score = this.score,
    activity = this.score,
    bloodFlow = this.bloodFlow,
    focus = this.focus,
  )
}
