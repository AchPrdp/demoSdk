package com.app.mylibrary

import android.content.Context
import com.app.mylibrary.db.entity.Events
import com.app.mylibrary.repo.EventRepository
import com.app.mylibrary.repo.SessionRepository
import com.app.mylibrary.repo.SessionRepositoryImpl
import com.app.mylibrary.sessionHelper.SessionManager

class MySdkImpl : IMySdkObject {

    private lateinit var eventRepository: EventRepository
    private lateinit var sessionRepository: SessionRepository
    private lateinit var sessionManager: SessionManager

    override lateinit var applicationContext: Context

    override var isSdkInitialize: Boolean = false

    private val initLock: Any = Any()

    override fun initializeSdk(context: Context) {
        if (!isSdkInitialize)
            synchronized(initLock) {
                if (isSdkInitialize) { // Double-checked locking
                    return
                }

                // implement sdk initialize logic with application context
                applicationContext = context
                println("SessionManager : initializeSdk")
                sessionManager = SessionManager()
                sessionRepository = sessionRepo

                isSdkInitialize = true
            }
    }

    override suspend fun startSession() {  //sessionRepository: SessionRepository
        // implement session start logic
        if (!isSdkInitialize || !checkSessionRepoInit()) return
        sessionRepository.startSession(applicationContext)
    }

    override suspend fun endSession() {  //sessionRepository: SessionRepository
        // implement session end logic
        if (!isSdkInitialize || !checkSessionRepoInit() || !isSessionActive()) return
        sessionRepository.endSession(applicationContext)
    }

    override suspend fun addAnalyticsLog(key: String, value: String) {
        // implement add analytics event or log
        if (!isSdkInitialize || !(this::eventRepository.isInitialized)) return
        if (isSessionActive()) {
            eventRepository.recordEvent(
                event = Events(
                    eventKey = key,
                    eventName = value,
                    eventRecordTime = System.currentTimeMillis()
                )
            )
        } else {
            println("Please start session to log events")
        }
    }

    override fun initEventRepo(eventRepository: EventRepository) {
        this.eventRepository = eventRepository
    }

    private val sessionRepo: SessionRepository by lazy {
        SessionRepositoryImpl(sessionManager = sessionManager)
    }

    override fun isSessionActive(): Boolean {
        return if (this::sessionManager.isInitialized) {
            sessionManager.isSessionActive()
        } else {
            false
        }
    }

    private fun checkSessionRepoInit(): Boolean {
        return this::sessionRepository.isInitialized
    }
}