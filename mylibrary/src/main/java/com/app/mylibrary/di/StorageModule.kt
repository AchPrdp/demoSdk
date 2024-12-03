package com.app.mylibrary.di

import android.content.Context
import androidx.room.Room
import com.app.mylibrary.constant.Constants
import com.app.mylibrary.db.YoDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideYoDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): YoDatabase {
//        val typeConverter = Convertors(moshi)
        return Room.databaseBuilder(
            context,
            YoDatabase::class.java,
            Constants.YO_DB
        )
//            .addTypeConverter(typeConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideEventsDao(database: YoDatabase) = database.getEventsDao()

    @Singleton
    @Provides
    fun provideSessionDao(database: YoDatabase) = database.getSessionDao()
}
