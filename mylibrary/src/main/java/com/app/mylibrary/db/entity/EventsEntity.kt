package com.app.mylibrary.db.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "events")
data class Events(
    val eventKey: String,
    val eventName: String,
    val eventRecordTime: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}