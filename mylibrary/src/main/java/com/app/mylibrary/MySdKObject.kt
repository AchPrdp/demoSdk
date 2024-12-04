package com.app.mylibrary

import android.content.Context
import com.app.mylibrary.api.ResponseBodyDto
import com.app.mylibrary.util.NetworkResult

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
    suspend fun syncEvents(): NetworkResult<ResponseBodyDto> {
        return mySdkObj.syncEvents()
    }

    private val mySdkObj: IMySdkObject by lazy {
        MySdkImpl()
    }
}