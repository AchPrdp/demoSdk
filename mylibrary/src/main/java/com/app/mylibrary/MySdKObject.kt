package com.app.mylibrary

import android.content.Context
import com.app.mylibrary.repo.EventRepository

object MySdKObject {

    @JvmStatic
    val isSdkInitialize: Boolean
        get() = mySdkObj.isSdkInitialize

    val isSessionActive: Boolean
        get() = mySdkObj.isSessionActive()

    @JvmStatic
    fun initWithContext(
        context: Context,
    ) {
        mySdkObj.initializeSdk(context)
    }

    @JvmStatic
    suspend fun startSession() {
        mySdkObj.startSession()
    }

    @JvmStatic
    suspend fun endSession() {
        mySdkObj.endSession()
    }

    @JvmStatic
    suspend fun addEventLog(
        key: String, value: String
    ) {
        mySdkObj.addAnalyticsLog(key = key, value = value)
    }

    @JvmStatic
    fun initEventRepo(eventRepository: EventRepository) {
        mySdkObj.initEventRepo(eventRepository)
    }

    private val mySdkObj: IMySdkObject by lazy {
        MySdkImpl()
    }
}