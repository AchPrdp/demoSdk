package com.app.mylibrary.db.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "session")
data class Session(
    val sessionName: String,
    val eventRecords: Int,
    val sessionStartTime: Long,
    val sessionEndTime: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}