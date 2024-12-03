package com.app.mylibrary.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.app.mylibrary.repo.APP_SESSION
import com.app.mylibrary.repo.APP_SESSION_LOG

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


suspend fun <T> DataStore<Preferences>.editDataStore(
    key: Preferences.Key<T>,
    value: T
) {
    this.edit { preferences ->
        preferences[key] = value
    }
}

fun <T> DataStore<Preferences>.getData(key: Preferences.Key<T>): Flow<T?> {
    return this.data.map { preferences ->
        preferences[key]
    }
}

val Context.appSessionActive by preferencesDataStore(
    APP_SESSION
)

val Context.sessionLog by preferencesDataStore(
    APP_SESSION_LOG
)


