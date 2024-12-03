package com.app.mylibrary.sessionHelper

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.mylibrary.datastore.editDataStore
import com.app.mylibrary.datastore.sessionLog
import com.app.mylibrary.repo.APP_SESSION

class SessionManager() {
    private var isSessionActive = false
    private var sessionStartTime: Long = 0

    fun isSessionActive() = isSessionActive

    suspend fun startSession(context: Context) {
        if (isSessionActive) return
        println("SessionManager : Session Started")
        isSessionActive = true
        sessionStartTime = System.currentTimeMillis()
        // Save session start to persistence
        saveSessionData(context, sessionStartTime)
    }

    suspend fun stopSession(context: Context) {
        if (!isSessionActive) return
        println("SessionManager : Session Stopped")
        val sessionDuration = System.currentTimeMillis() - sessionStartTime
        isSessionActive = false
        // Save session duration or handle session end logic
        saveSessionData(context, sessionDuration)
    }

    private suspend fun saveSessionData(context: Context, value: Long) {
        val appSessionKey = stringPreferencesKey(APP_SESSION)
        context.sessionLog.editDataStore(
            appSessionKey,
            value.toString()
        )
    }
}