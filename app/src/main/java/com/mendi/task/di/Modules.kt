package com.mendi.task.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mendi.task.screens.session.data.repository.SessionRepositoryImpl
import com.mendi.task.screens.session.domain.SessionRepository
import com.mendi.task.screens.settings.data.repository.GameSettingsRepositoryImpl
import com.mendi.task.screens.settings.domain.GameSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {
  @Singleton
  @Binds
  abstract fun bindsSessionRepository(
    impl: SessionRepositoryImpl,
  ): SessionRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class GameSettingsModule {
  @Singleton
  @Binds
  abstract fun bindsGamesSettingsRepository(
    impl: GameSettingsRepositoryImpl,
  ): GameSettingsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Firebase {
  @Singleton
  @Provides
  fun bindsSessionRepository(): FirebaseFirestore {
    return Firebase.firestore
  }
}
