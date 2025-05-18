package com.mendi.task.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import com.mendi.task.screens.session.data.repository.SessionRepositoryImpl
import com.mendi.task.screens.session.domain.SessionRepository
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
object Firebase {
  @Singleton
  @Provides
  fun bindsSessionRepository(): FirebaseFirestore {
    val settings = FirebaseFirestoreSettings.Builder().setLocalCacheSettings(
      memoryCacheSettings {},
    ).build()
    return Firebase.firestore.apply {
      this.firestoreSettings = settings
    }
  }
}
