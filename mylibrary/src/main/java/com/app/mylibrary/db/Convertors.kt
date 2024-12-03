package com.app.mylibrary.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import java.time.LocalDateTime

@ProvidedTypeConverter
class Convertors(private val moshi: Moshi) {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime): String {
        return moshi.adapter(LocalDateTime::class.java).toJson(localDateTime)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun stringToLocalDateTime(value: String): LocalDateTime? {
        return moshi.adapter(LocalDateTime::class.java).fromJson(value)
    }

}