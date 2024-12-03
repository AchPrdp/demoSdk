package com.app.mylibrary.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.mylibrary.db.entity.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert
    suspend fun insertSession(session: Session)

    @Query("SELECT * FROM session")
    fun getSessions(): Flow<List<Session>>

    @Query("DELETE FROM session WHERE id =:sessionId")
    suspend fun deleteSession(sessionId: String)

    @Query("UPDATE session SET eventRecords = (eventRecords + 1) WHERE id = :sessionId")
    suspend fun updateRecordedEventCount(sessionId: String)

    @Query("UPDATE session SET sessionEndTime = :sessionEndTime WHERE id = :sessionId")
    suspend fun updateSessionEndTime(sessionEndTime: String, sessionId: String)
}