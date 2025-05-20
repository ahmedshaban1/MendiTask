package com.mendi.task.screens.settings.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.mendi.task.core.domain.DataError
import com.mendi.task.core.domain.DataError.Remote.SERVER
import com.mendi.task.core.domain.Result
import com.mendi.task.screens.settings.data.entities.GameEntity
import com.mendi.task.screens.settings.data.entities.games
import com.mendi.task.screens.settings.data.mappers.toGame
import com.mendi.task.screens.settings.data.mappers.toGameEntity
import com.mendi.task.screens.settings.domain.Game
import com.mendi.task.screens.settings.domain.GameSettingsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

private const val DOCUMENT_NAME = "games_settings"

class GameSettingsRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : GameSettingsRepository {
  override suspend fun getGamesSettings() =
    callbackFlow<Result<List<Game>, DataError.Remote>> {
      val listener =
        db.collection(
          DOCUMENT_NAME,
        ).orderBy("order", Query.Direction.ASCENDING).addSnapshotListener { snapshot, error ->
          if (error != null) {
            trySend(Result.Error(SERVER))
            return@addSnapshotListener
          }

          if (snapshot != null) {
            val games = snapshot.documents.mapNotNull {
              it.toObject(GameEntity::class.java)?.toGame(it.id)
            }
            if (games.isEmpty()) {
              createGamesSettings(
                onError = {
                  trySend(Result.Error(SERVER))
                },
              )
            } else {
              trySend(Result.Success(games))
            }
          }
        }

      awaitClose { listener.remove() }
    }

  override suspend fun updateGameSettings(
    game: Game,
  ) = callbackFlow<Result<Any, DataError.Remote>> {
    db.collection(DOCUMENT_NAME)
      .document(game.id!!)
      .set(game.toGameEntity())
      .addOnFailureListener { e ->
        trySend(Result.Error(SERVER))
      }
    awaitClose()
  }

  private fun createGamesSettings(
    onError: () -> Unit,
  ) {
    val batch = db.batch()
    games.forEach { game ->
      val docRef = db.collection(
        DOCUMENT_NAME,
      ).document()
      batch.set(docRef, game)
    }

    batch.commit().addOnFailureListener { e ->
      onError()
    }
  }
}
