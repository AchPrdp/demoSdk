package com.app.mylibrary.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsApi {

    @GET("bf52-7b06-4261-a532")
    suspend fun getEvents()
            : Response<ResponseBodyDto>

    @POST("post_api_slug")
    suspend fun postEvents(
        @Body data: RequestDto
    ): Response<ResponseBodyDto>
}

data class RequestDto(val abc: String)

@JsonClass(generateAdapter = true)
data class ResponseBodyDto(
    @Json(name = "data")
    val data: List<ResponseDto>
)

@JsonClass(generateAdapter = true)
data class ResponseDto(
    @Json(name = "eventId") val eventId: String,
    @Json(name = "eventAction") val eventAction: String,
    @Json(name = "eventName") val eventName: String,
)
