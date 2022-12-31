package com.example.hacknews.data.events

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.hacknews.model.Item

interface EventsRepository {

    val events: LiveData<List<Item>>

    /**
     * Get HackNews events.
     */
    suspend fun getEvents(requestUrl: String, context: Context)
}