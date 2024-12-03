package com.app.mylibrary.di

import android.content.Context
import com.app.mylibrary.sessionHelper.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context,
    ): SessionManager {
        return SessionManager()
    }

   /* @Singleton
    @Provides
    fun provideEventManager(
        sessionManager: SessionManager,
    ): EventManager {
        return EventManager()
    }*/

}