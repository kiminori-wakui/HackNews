package com.example.hacknews.data.events.impl

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.hacknews.R
import com.example.hacknews.data.MySingleton
import com.example.hacknews.data.events.EventsRepository
import com.example.hacknews.data.posts.impl.paragraphsPost4
import com.example.hacknews.data.posts.impl.publication
import com.example.hacknews.model.Item
import com.example.hacknews.model.Metadata
import com.example.hacknews.model.PostAuthor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of EventsRepository that returns a hardcoded list of
 * posts with resources after some delay in a background thread.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class EventsRepositoryImpl() : EventsRepository {

    companion object {
        private const val TAG = "EventsRepositoryImpl"
    }

    private val _events = MutableLiveData<List<Item>>(listOf())
    override val events: LiveData<List<Item>> = _events

    private val mutex = Mutex()

    override suspend fun getEvents(requestUrl: String, context: Context) {
        mutex.withLock {
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, requestUrl, null,
                { response ->
                    val items = mutableListOf<Item>()
                    val events = response.getJSONArray("events")
                    for (i in 0 until events.length()) {
                        val event = events.getJSONObject(i)
                        val title = event.getString("title")
                        val startedAt = event.getString("started_at")
                        val date = parseEventDate(startedAt)
                        val url = event.getString("event_url")
                        items.add(
                            Item(
                                id = "84eb677660d9",
                                title = title,
                                subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
                                url = url,
                                publication = publication,
                                metadata = Metadata(
                                    author = PostAuthor(name = date),
                                    date = date,
                                    readTimeMinutes = 1
                                ),
                                paragraphs = paragraphsPost4,
                                imageId = R.drawable.post_4,
                                imageThumbId = R.drawable.post_4_thumb
                            )
                        )
                    }
                    _events.value = items
                },
                { error ->
                    Log.e(TAG, error.message.toString())
                }
            )
            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(context = context).addToRequestQueue(jsonObjectRequest)
        }
    }

    @SuppressLint("SimpleDateFormat", "NewApi")
    private fun parseEventDate(startedAt: String?): String {
        startedAt ?: return ""
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        val dt = df.parse(startedAt)
        val df2 = SimpleDateFormat("M/d")
        return df2.format(dt as Date)
    }
}