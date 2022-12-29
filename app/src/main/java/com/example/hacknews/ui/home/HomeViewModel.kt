/*
 * Copyright 2021 The Android Open Source Project
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

package com.example.hacknews.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.hacknews.R
import com.example.hacknews.data.interests.MySingleton
import com.example.hacknews.data.posts.PostsRepository
import com.example.hacknews.data.posts.impl.*
import com.example.hacknews.model.Metadata
import com.example.hacknews.model.Item
import com.example.hacknews.model.PostAuthor
import com.example.hacknews.model.ItemsFeed
import com.example.hacknews.utils.ErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
    val searchInput: String

    /**
     * There are no posts to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoPosts(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState

    /**
     * There are posts to render, as contained in [postsFeed].
     *
     * There is guaranteed to be a [selectedItem], which is one of the posts from [postsFeed].
     */
    data class HasPosts(
        val postsFeed: ItemsFeed,
        val selectedItem: Item,
        val isArticleOpen: Boolean,
        val favorites: Set<String>,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState
}

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class HomeViewModelState(
    val itemsFeed: ItemsFeed? = null,
    val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): HomeUiState =
        if (itemsFeed == null) {
            HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.HasPosts(
                postsFeed = itemsFeed,
                // Determine the selected post. This will be the post the user last selected.
                // If there is none (or that post isn't in the current feed), default to the
                // highlighted post
                selectedItem = itemsFeed.allItems.find {
                    it.id == selectedPostId
                } ?: itemsFeed.highlightedItem,
                isArticleOpen = isArticleOpen,
                favorites = favorites,
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        }
}

/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel(
    private val postsRepository: PostsRepository,
    private val context: Context,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshPosts()

        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            requestQiitaWebApi()
            requestConnpassWebApi()

            postsRepository.observeFavorites().collect { favorites ->
                viewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    private fun requestQiitaWebApi() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, WEB_API_KEY_QIITA, null,
            Response.Listener { response ->
                val items = mutableListOf<Item>()
                for (i in 0 until response.length()) {
                    val post = response.getJSONObject(i)
                    val title = post.getString("title")
                    val url = post.getString("url")
                    val user = post.getJSONObject("user")
                    val imageUrl = user.getString("profile_image_url")
                    items.add(
                        Item(
                            id = "84eb677660d9",
                            title = title,
                            subtitle = "TL;DR: Expose resource IDs from ViewModels to avoid showing obsolete data.",
                            url = url,
                            publication = publication,
                            metadata = Metadata(
                                author = PostAuthor(name = "name"),
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
                viewModelState.update {
                    val itemsFeed = it.itemsFeed ?: ItemsFeed(
                        highlightedItem = item4,
                        recentPosts = listOf(
                            item3.copy(id = "post8"),
                            item4.copy(id = "post9"),
                            item5.copy(id = "post10")
                        ),
                        recentEvents = listOf(
                            item5,
                            item1.copy(id = "post6"),
                            item2.copy(id = "post7")
                        )
                    )
                    it.copy(
                        itemsFeed = ItemsFeed(
                            highlightedItem = itemsFeed.highlightedItem,
                            recentPosts = items,
                            recentEvents = itemsFeed.recentEvents
                        ),
                        isLoading = false
                    )
                }
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
                error.stackTrace
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context = context).addToRequestQueue(jsonArrayRequest)
    }

    private fun requestConnpassWebApi() {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, WEB_API_KEY_CONNPASS, null,
            Response.Listener { response ->
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
                viewModelState.update {
                    val itemsFeed = it.itemsFeed ?: ItemsFeed(
                        highlightedItem = item4,
                        recentPosts = listOf(
                            item3.copy(id = "post8"),
                            item4.copy(id = "post9"),
                            item5.copy(id = "post10")
                        ),
                        recentEvents = listOf(
                            item5,
                            item1.copy(id = "post6"),
                            item2.copy(id = "post7")
                        )
                    )
                    it.copy(
                        itemsFeed = ItemsFeed(
                            highlightedItem = itemsFeed.highlightedItem,
                            recentPosts = itemsFeed.recentPosts,
                            recentEvents = items
                        ),
                        isLoading = false
                    )
                }
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context = context).addToRequestQueue(jsonObjectRequest)
    }

    @SuppressLint("SimpleDateFormat", "NewApi")
    private fun parseEventDate(startedAt: String?): String {
        startedAt ?: return ""
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        val dt = df.parse(startedAt)
        val df2 = SimpleDateFormat("M/d")
        return df2.format(dt)
    }

    /**
     * Refresh posts and update the UI state accordingly
     */
    fun refreshPosts() {
        // Ui state is refreshing
//        viewModelState.update { it.copy(isLoading = true) }
//
//        viewModelScope.launch {
//            val result = postsRepository.getPostsFeed()
//            viewModelState.update {
//                when (result) {
//                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
//                    is Result.Error -> {
//                        val errorMessages = it.errorMessages + ErrorMessage(
//                            id = UUID.randomUUID().mostSignificantBits,
//                            messageId = R.string.load_error
//                        )
//                        it.copy(errorMessages = errorMessages, isLoading = false)
//                    }
//                }
//            }
//        }
    }

    /**
     * Toggle favorite of a post
     */
    fun toggleFavourite(postId: String) {
        viewModelScope.launch {
            postsRepository.toggleFavorite(postId)
        }
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectArticle(url: String) {
        // Treat selecting a detail as simply interacting with it
//        interactedWithArticleDetails(postId)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(context, intent, null)
    }

    /**
     * Notify that an error was displayed on the screen
     */
    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    /**
     * Notify that the user interacted with the feed
     */
    fun interactedWithFeed() {
        viewModelState.update {
            it.copy(isArticleOpen = false)
        }
    }

    /**
     * Notify that the user interacted with the article details
     */
    fun interactedWithArticleDetails(postId: String) {
        viewModelState.update {
            it.copy(
                selectedPostId = postId,
                isArticleOpen = true
            )
        }
    }

    /**
     * Notify that the user updated the search query
     */
    fun onSearchInputChanged(searchInput: String) {
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    /**
     * Factory for HomeViewModel that takes PostsRepository as a dependency
     */
    companion object {
        private const val WEB_API_KEY_CONNPASS = "https://connpass.com/api/v1/event/"
        private const val WEB_API_KEY_QIITA = "https://qiita.com/api/v2/items"

        fun provideFactory(
            postsRepository: PostsRepository,
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(postsRepository, context) as T
            }
        }
    }
}
