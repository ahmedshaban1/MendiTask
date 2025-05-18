package com.mendi.task.screens.session.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.session.data.entities.SessionEntity
import com.mendi.task.screens.session.data.mappers.toSession
import com.mendi.task.screens.session.data.mappers.toSessionEntity
import com.mendi.task.screens.session.domain.Session
import com.mendi.task.screens.session.domain.SessionRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

private const val DOCUMENT_NAME = "sessions"

class SessionRepositoryImpl @Inject constructor(
  private val db: FirebaseFirestore,
) : SessionRepository {
  override suspend fun createSession(
    session: Session,
  ) = callbackFlow {
    db.collection(DOCUMENT_NAME)
      .add(session.toSessionEntity())
      .addOnSuccessListener { _ ->
        trySend(Result.Success(Unit))
      }
      .addOnFailureListener { _ ->
        trySend(Result.Error(DataError.Remote.SERVER))
      }
    awaitClose()
  }

  override suspend fun getSessions() =
    callbackFlow<Result<List<Session>, DataError.Remote>> {
      val listener = db.collection(DOCUMENT_NAME)
        .orderBy("timestamp", Query.Direction.DESCENDING)
        .addSnapshotListener { snapshot, error ->
          if (error != null) {
            trySend(Result.Error(DataError.Remote.SERVER))
            return@addSnapshotListener
          }

          if (snapshot != null) {
            val sessions = snapshot.documents.mapNotNull {
              it.toObject(SessionEntity::class.java)
            }
            trySend(Result.Success(sessions.map { it.toSession() }))
          }
        }

      awaitClose { listener.remove() }
    }
}
