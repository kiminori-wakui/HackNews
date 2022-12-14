/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hacknews.data.posts.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.hacknews.R
import com.example.hacknews.data.MySingleton
import com.example.hacknews.data.Result
import com.example.hacknews.data.posts.PostsRepository
import com.example.hacknews.model.Item
import com.example.hacknews.model.ItemsFeed
import com.example.hacknews.model.Metadata
import com.example.hacknews.model.PostAuthor
import com.example.hacknews.utils.addOrRemove
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Implementation of PostsRepository that returns a hardcoded list of
 * posts with resources after some delay in a background thread.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PostsRepositoryImpl : PostsRepository {

    companion object {
        private const val TAG = "PostsRepositoryImpl"
    }

    private val _posts = MutableLiveData<List<Item>>(listOf())
    override val posts: LiveData<List<Item>> = _posts

    // for now, store these in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

    override suspend fun getPost(postId: String?): Result<Item> {
        return withContext(Dispatchers.IO) {
            val post = postsDummy.allItems.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Post not found"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPosts(requestUrl: String, context: Context) {
        mutex.withLock {
            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, requestUrl, null,
                { response ->
                    val items = mutableListOf<Item>()
                    for (i in 0 until response.length()) {
                        val post = response.getJSONObject(i)
                        val title = post.getString("title")
                        val url = post.getString("url")
                        val user = post.getJSONObject("user")
                        val imageUrl = user.getString("profile_image_url")
                        val tags = post.getJSONArray("tags")
                        var tagsName = ""
                        for (j in 0 until tags.length()) {
                            val tag = tags.getJSONObject(j)
                            if (j > 0) {
                                tagsName += ", "
                            }
                            tagsName += tag.getString("name")
                        }
                        items.add(
                            Item(
                                id = "84eb677660d9",
                                title = title,
                                subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
                                url = url,
                                publication = publication,
                                metadata = Metadata(
                                    author = PostAuthor(name = tagsName),
                                    date = "date",
                                    readTimeMinutes = 1
                                ),
                                paragraphs = paragraphsPost4,
                                imageUrl = imageUrl,
                                imageId = R.drawable.post_4,
                                imageThumbId = R.drawable.post_4_thumb
                            )
                        )
                    }
                    _posts.value = items
                },
                { error ->
                    Log.e(TAG, error.message.toString())
                }
            )
            MySingleton.getInstance(context = context).addToRequestQueue(jsonArrayRequest)
        }
    }

    override suspend fun getPostsFeed(): Result<ItemsFeed> {
        return withContext(Dispatchers.IO) {
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                Result.Success(postsDummy)
            }
        }
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        mutex.withLock {
            val set = favorites.value.toMutableSet()
            set.addOrRemove(postId)
            favorites.value = set.toSet()
        }
    }

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}
