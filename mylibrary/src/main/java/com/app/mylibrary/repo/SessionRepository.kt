package com.app.mylibrary.repo

import android.content.Context
import com.app.mylibrary.sessionHelper.SessionManager
import javax.inject.Inject

interface SessionRepository {
    suspend fun startSession(context: Context)
    suspend fun endSession(context: Context)
}

class SessionRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager
) : SessionRepository {
    override suspend fun startSession(context: Context) {
        sessionManager.startSession(context)
    }

    override suspend fun endSession(context: Context) {
        sessionManager.stopSession(context)
    }
}