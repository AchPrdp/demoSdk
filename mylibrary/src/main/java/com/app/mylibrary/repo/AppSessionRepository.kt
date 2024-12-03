package com.app.mylibrary.repo

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.mylibrary.datastore.appSessionActive
import com.app.mylibrary.datastore.editDataStore
import com.app.mylibrary.datastore.getData
import com.app.mylibrary.datastore.sessionLog
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val APP_SESSION = "app_session"
const val APP_SESSION_LOG = "app_session_log"

@Deprecated("Not Used")
interface AppSessionRepository {

    suspend fun updateSessionActive(status: Boolean)

    fun getSessionList(): Flow<AppSession?>

    suspend fun updateSessionList(items: AppSession)
}

data class AppSession(val data: String)

@Deprecated("Not Used")
class AppSessionRepositoryImpl @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : AppSessionRepository {

    private val appSessionKey = stringPreferencesKey(APP_SESSION)
    private val appSessionStatus = booleanPreferencesKey(APP_SESSION_LOG)

    override suspend fun updateSessionActive(status: Boolean) {
        appContext.appSessionActive.editDataStore(appSessionStatus, status)
    }

    override fun getSessionList(): Flow<AppSession?> {
        return appContext.sessionLog.getData(appSessionKey).map {
            AppSession(data = it ?: "")
        }
    }

    override suspend fun updateSessionList(items: AppSession) {
        appContext.sessionLog.editDataStore(appSessionKey, items.data)
    }
}

