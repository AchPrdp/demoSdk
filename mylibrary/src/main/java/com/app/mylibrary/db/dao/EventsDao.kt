package com.app.mylibrary.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.mylibrary.db.entity.Events
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Insert
    suspend fun insertEvent(event: Events)

    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Events>>

    @Query("DELETE FROM events WHERE id =:eventId")
    suspend fun deleteEvent(eventId: String)
}