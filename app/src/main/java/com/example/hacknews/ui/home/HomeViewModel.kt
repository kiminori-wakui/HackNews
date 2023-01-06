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

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.hacknews.R
import com.example.hacknews.data.events.EventsRepository
import com.example.hacknews.data.interests.TopicSelection
import com.example.hacknews.data.posts.PostsRepository
import com.example.hacknews.model.Metadata
import com.example.hacknews.model.Item
import com.example.hacknews.model.PostAuthor
import com.example.hacknews.model.ItemsFeed
import com.example.hacknews.utils.ErrorMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
    private val eventsRepository: EventsRepository,
    private val context: Context,
) : ViewModel(), LifecycleObserver {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    private val selectedTopics = MutableStateFlow<Set<TopicSelection>>(setOf())

    private val postsObserver = Observer<List<Item>> { posts ->
        updateViewModelState(posts = posts)
    }

    private val eventsObserver = Observer<List<Item>> { events ->
        updateViewModelState(events = events)
    }

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        runBlocking {
            postsRepository.posts.observeForever(postsObserver)
            eventsRepository.events.observeForever(eventsObserver)
        }
        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            postsRepository.observeFavorites().collect { favorites ->
                viewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        postsRepository.posts.removeObserver(postsObserver)
        eventsRepository.events.removeObserver(eventsObserver)
    }

    /**
     * Refresh posts and update the UI state accordingly
     */
    fun refresh() {
        viewModelState.update { it.copy(isLoading = true) }

        val topics = selectedTopics.value
        if (topics.isEmpty()) {
            viewModelScope.launch {
                requestQiitaWebApi()
                requestConnpassWebApi()
            }
        } else {
            requestApiBySelectedTopics(topics)
        }
    }

    fun onSearchEvent() {
        viewModelState.update { it.copy(isLoading = true) }
        // refresh to reflect Interests
        viewModelScope.launch {
            requestQiitaWebApi()
            requestConnpassWebApi()
        }
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

    fun updateSelectedTopics(topics: Set<TopicSelection>) {
        if (selectedTopics.value != topics) {
            clearViewModelState()
            selectedTopics.value = topics
        }
        refresh()
    }

    /**
     * Notify that the user updated the search query
     */
    fun onSearchInputChanged(searchInput: String) {
        clearViewModelState()
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    private suspend fun requestQiitaWebApi(selectedKeyword: String = "") {
        var option = "?page=1"
        val searchInput = viewModelState.value.searchInput
        if (searchInput.isNotBlank()) {
            option += "&query=$searchInput"
        } else if (selectedKeyword.isNotBlank()) {
            option += "&query=$selectedKeyword"
        }
        postsRepository.getPosts(WEB_API_KEY_QIITA + option, context)
        delay(1000)
    }

    private suspend fun requestConnpassWebApi(selectedKeyword: String = "") {
        var option = "?order=2&count=20"
        val searchInput = viewModelState.value.searchInput
        if (searchInput.isNotBlank()) {
            option += "&keyword_or=$searchInput"
        } else if (selectedKeyword.isNotBlank()) {
            option += "&keyword_or=$selectedKeyword"
        }
        eventsRepository.getEvents(WEB_API_KEY_CONNPASS + option, context)
        delay(5000)
    }

    private fun clearViewModelState(
        highlightedItem: Item? = null,
        posts: List<Item>? = null,
        events: List<Item>? = null
    ) {
        viewModelState.update {
            it.copy(
                itemsFeed = ItemsFeed(
                    highlightedItem = highlightedItem ?: Item(
                        id = "0",
                        title = "none",
                        url = "",
                        metadata = Metadata(
                            author = PostAuthor(name = "name"),
                            date = "date",
                            readTimeMinutes = 1
                        ),
                        imageId = R.drawable.post_4,
                        imageThumbId = R.drawable.post_4_thumb
                    ),
                    recentPosts = posts ?: listOf(),
                    recentEvents = events ?: listOf()
                ),
                isLoading = false
            )
        }
    }

    private fun updateViewModelState(
        highlightedItem: Item? = null,
        posts: List<Item>? = null,
        events: List<Item>? = null
    ) {
        viewModelState.update {
            val itemsFeed = it.itemsFeed ?: ItemsFeed(
                highlightedItem = Item(
                    id = "0",
                    title = "none",
                    url = "",
                    metadata = Metadata(
                        author = PostAuthor(name = "name"),
                        date = "date",
                        readTimeMinutes = 1
                    ),
                    imageId = R.drawable.post_4,
                    imageThumbId = R.drawable.post_4_thumb
                ),
                recentPosts = listOf(),
                recentEvents = listOf()
            )
            val recentPosts = posts?.let { list ->
                itemsFeed.recentPosts + list
            } ?: itemsFeed.recentPosts
            val recentEvents = events?.let { list ->
                itemsFeed.recentEvents + list
            } ?: itemsFeed.recentEvents

            it.copy(
                itemsFeed = ItemsFeed(
                    highlightedItem = highlightedItem ?: itemsFeed.highlightedItem,
                    recentPosts = recentPosts.distinct(),
                    recentEvents = recentEvents.distinct()
                ),
                isLoading = false
            )
        }
    }

    private fun requestApiBySelectedTopics(topics: Set<TopicSelection>) {
        viewModelScope.launch {
            topics.forEach {
                requestQiitaWebApi(selectedKeyword = it.topic)
                requestConnpassWebApi(selectedKeyword = it.topic)
            }
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
            eventsRepository: EventsRepository,
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(postsRepository, eventsRepository, context) as T
            }
        }
    }
}
