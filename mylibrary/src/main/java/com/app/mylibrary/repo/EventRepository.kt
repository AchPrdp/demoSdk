package com.app.mylibrary.repo

import com.app.mylibrary.api.EventsApi
import com.app.mylibrary.api.ResponseBodyDto
import com.app.mylibrary.db.dao.EventsDao
import com.app.mylibrary.db.entity.Events
import com.app.mylibrary.util.NetworkConnectivityManager
import com.app.mylibrary.util.NetworkResult
import com.app.mylibrary.util.networkBoundResource
import javax.inject.Inject


interface EventRepository {
    suspend fun recordEvent(event: Events)

    suspend fun syncEvent()
            : NetworkResult<ResponseBodyDto>

    suspend fun deleteEvent(eventId: String)
}

class EventRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao,
    private val eventsApi: EventsApi,
    private val connectivityManager: NetworkConnectivityManager
) : EventRepository {
    override suspend fun recordEvent(event: Events) {
        eventsDao.insertEvent(event)
    }

    override suspend fun syncEvent(): NetworkResult<ResponseBodyDto> {
        return networkBoundResource(
            apiCall = { eventsApi.getEvents() },
            shouldFetch = { connectivityManager.isConnectedToNetwork() }
        )
    }

    override suspend fun deleteEvent(eventId: String) {
        eventsDao.deleteEvent(eventId)
    }
}
