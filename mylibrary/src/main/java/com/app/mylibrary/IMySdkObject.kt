package com.app.mylibrary

import android.content.Context
import com.app.mylibrary.repo.EventRepository

interface IMySdkObject {

    var applicationContext: Context

    var isSdkInitialize: Boolean

    fun isSessionActive(): Boolean

    fun initializeSdk(context: Context)

    suspend fun startSession()

    suspend fun endSession()

    suspend fun addAnalyticsLog(key: String, value: String)

    fun initEventRepo(eventRepository: EventRepository)
}