package com.app.mylibrary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.mylibrary.db.dao.EventsDao
import com.app.mylibrary.db.dao.SessionDao
import com.app.mylibrary.db.entity.Events
import com.app.mylibrary.db.entity.Session

@Database(entities = [Events::class, Session::class], version = 1, exportSchema = false)
//@TypeConverters(Convertors::class)
abstract class YoDatabase : RoomDatabase() {
    abstract fun getEventsDao(): EventsDao
    abstract fun getSessionDao(): SessionDao
}