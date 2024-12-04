package com.app.mylibrary

import com.app.mylibrary.repo.EventRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RepoEntryPoint {
    fun eventRepository(): EventRepository
}