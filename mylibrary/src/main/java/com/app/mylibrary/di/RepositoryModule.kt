package com.app.mylibrary.di


import com.app.mylibrary.api.EventsApi
import com.app.mylibrary.db.dao.EventsDao
import com.app.mylibrary.repo.EventRepository
import com.app.mylibrary.repo.EventRepositoryImpl
import com.app.mylibrary.repo.SessionRepository
import com.app.mylibrary.repo.SessionRepositoryImpl
import com.app.mylibrary.sessionHelper.SessionManager
import com.app.mylibrary.util.NetworkConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEventRepository(
        eventsDao: EventsDao,
        eventsApi: EventsApi,
        connectivityManager: NetworkConnectivityManager
    ): EventRepository {
        return EventRepositoryImpl(eventsDao, eventsApi, connectivityManager)
    }

    @Singleton
    @Provides
    fun provideSessionRepository(
        sessionManager: SessionManager
    ): SessionRepository {
        return SessionRepositoryImpl(sessionManager)
    }

    /*@Singleton
    @Provides
    fun provideAppSessionRepository(
        appContext: Context
    ): AppSessionRepository {
        return AppSessionRepositoryImpl(appContext)
    }

    @Singleton
    @Provides
    fun provideIMySdkObject(

    ): IMySdkObject {
        return MySdkImpl()
    }*/
}
