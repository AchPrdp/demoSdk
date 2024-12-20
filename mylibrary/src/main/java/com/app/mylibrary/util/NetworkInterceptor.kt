package com.app.mylibrary.util

import okhttp3.Interceptor
import okhttp3.Response

//interface NetworkInterceptor : Interceptor

class NetworkInterceptorHelper : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().build()
        return chain.proceed(request)
    }
}