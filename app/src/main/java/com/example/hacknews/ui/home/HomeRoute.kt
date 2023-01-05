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

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import com.example.hacknews.R
import com.example.hacknews.model.Item
import com.example.hacknews.ui.article.ArticleScreen
import com.example.hacknews.ui.base.tabContainerModifier
import com.example.hacknews.ui.interests.*

enum class HomeSections(@StringRes val titleResId: Int) {
    Post(R.string.home_section_post),
    Event(R.string.home_section_event)
}

/**
 * TabContent for a single tab of the screen.
 *
 * This is intended to encapsulate a tab & it's content as a single object. It was added to avoid
 * passing several parameters per-tab from the stateful composable to the composable that displays
 * the current tab.
 *
 * @param section the tab that this content is for
 * @param section content of the tab, a composable that describes the content
 */
class HomeTabContent(val section: HomeSections, val content: @Composable () -> Unit)

/**
 * Displays the Home route.
 *
 * Note: AAC ViewModels don't work with Compose Previews currently.
 *
 * @param homeViewModel ViewModel that handles the business logic of this screen
 * @param isExpandedScreen (state) whether the screen is expanded
 * @param openDrawer (event) request opening the app drawer
 * @param scaffoldState (state) state for the [Scaffold] component on this screen
 */
@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    interestsViewModel: InterestsViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    onClickSearchIcon: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    // UiState of the HomeScreen
    val uiState by homeViewModel.uiState.collectAsState()
    val selectedTopics by interestsViewModel.selectedTopics.collectAsState()
    val tabContent = rememberHomeTabContent(homeViewModel, uiState)

    LaunchedEffect(selectedTopics) {
        homeViewModel.updateSelectedTopics(selectedTopics)
    }

    HomeRoute(
        uiState = uiState,
        homeTabContent = tabContent,
        isExpandedScreen = isExpandedScreen,
        onToggleFavorite = { homeViewModel.toggleFavourite(it) },
        onRefreshPosts = { homeViewModel.refresh() },
        onErrorDismiss = { homeViewModel.errorShown(it) },
        onInteractWithFeed = { homeViewModel.interactedWithFeed() },
        onInteractWithArticleDetails = { homeViewModel.interactedWithArticleDetails(it) },
        onSearchInputChanged = { homeViewModel.onSearchInputChanged(it) },
        openDrawer = openDrawer,
        onClickSearchIcon = onClickSearchIcon,
        onSearch = { homeViewModel.onSearchEvent() },
        scaffoldState = scaffoldState,
    )
}

/**
 * Remembers the content for each tab on the Home screen
 * gathering application data from [InterestsViewModel]
 */
@Composable
fun rememberHomeTabContent(homeViewModel: HomeViewModel, uiState: HomeUiState): List<HomeTabContent> {
    var favorites = setOf("")
    var recentPosts = listOf<Item>()
    var recentEvent = listOf<Item>()
    if (uiState is HomeUiState.HasPosts) {
        favorites = uiState.favorites
        recentPosts = uiState.postsFeed.recentPosts
        recentEvent = uiState.postsFeed.recentEvents
    }

    val postSection = HomeTabContent(HomeSections.Post) {
        PostListSection(
            recentPosts,
            { homeViewModel.selectArticle(it) },
            favorites,
        ) { homeViewModel.toggleFavourite(it) }
    }

    val eventSection = HomeTabContent(HomeSections.Event) {
        EventListSimpleSection(
            recentEvent,
            { homeViewModel.selectArticle(it) },
            favorites,
        ) { homeViewModel.toggleFavourite(it) }
    }

    return listOf(postSection, eventSection)
}

/**
 * Displays the Home route.
 *
 * This composable is not coupled to any specific state management.
 *
 * @param uiState (state) the data to show on the screen
 * @param isExpandedScreen (state) whether the screen is expanded
 * @param onToggleFavorite (event) toggles favorite for a post
 * @param onRefreshPosts (event) request a refresh of posts
 * @param onErrorDismiss (event) error message was shown
 * @param onInteractWithFeed (event) indicate that the feed was interacted with
 * @param onInteractWithArticleDetails (event) indicate that the article details were interacted
 * with
 * @param openDrawer (event) request opening the app drawer
 * @param scaffoldState (state) state for the [Scaffold] component on this screen
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(
    uiState: HomeUiState,
    homeTabContent: List<HomeTabContent>,
    isExpandedScreen: Boolean,
    onToggleFavorite: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithFeed: () -> Unit,
    onInteractWithArticleDetails: (String) -> Unit,
    onSearchInputChanged: (String) -> Unit,
    openDrawer: () -> Unit,
    onClickSearchIcon: () -> Unit,
    onSearch: () -> Unit,
    scaffoldState: ScaffoldState,
) {
    // Construct the lazy list states for the list and the details outside of deciding which one to
    // show. This allows the associated state to survive beyond that decision, and therefore
    // we get to preserve the scroll throughout any changes to the content.
    val homeListLazyListState = rememberLazyListState()
    val articleDetailLazyListStates = when (uiState) {
        is HomeUiState.HasPosts -> uiState.postsFeed.allItems
        is HomeUiState.NoPosts -> emptyList()
    }.associate { post ->
        key(post.id) {
            post.id to rememberLazyListState()
        }
    }

    when (getHomeScreenType(isExpandedScreen, uiState)) {
        HomeScreenType.FeedWithArticleDetails -> {
            HomeFeedWithArticleDetailsScreen(
                uiState = uiState,
                homeTabContent = homeTabContent,
                showTopAppBar = !isExpandedScreen,
                onToggleFavorite = onToggleFavorite,
                onRefreshPosts = onRefreshPosts,
                onErrorDismiss = onErrorDismiss,
                onInteractWithList = onInteractWithFeed,
                onInteractWithDetail = onInteractWithArticleDetails,
                openDrawer = openDrawer,
                onClickSearch = onClickSearchIcon,
                homeListLazyListState = homeListLazyListState,
                articleDetailLazyListStates = articleDetailLazyListStates,
                scaffoldState = scaffoldState,
                onSearchInputChanged = onSearchInputChanged,
                onSearch = onSearch
            )
        }
        HomeScreenType.Feed -> {
            HomeFeedScreen(
                uiState = uiState,
                homeTabContent = homeTabContent,
                showTopAppBar = !isExpandedScreen,
                onRefreshPosts = onRefreshPosts,
                onErrorDismiss = onErrorDismiss,
                openDrawer = openDrawer,
                onClickSearch = onClickSearchIcon,
                homeListLazyListState = homeListLazyListState,
                scaffoldState = scaffoldState,
                onSearchInputChanged = onSearchInputChanged,
                onSearch = onSearch
            )
        }
        HomeScreenType.ArticleDetails -> {
            // Guaranteed by above condition for home screen type
            check(uiState is HomeUiState.HasPosts)

            ArticleScreen(
                item = uiState.selectedItem,
                isExpandedScreen = isExpandedScreen,
                onBack = onInteractWithFeed,
                isFavorite = uiState.favorites.contains(uiState.selectedItem.id),
                onToggleFavorite = {
                    onToggleFavorite(uiState.selectedItem.id)
                },
                lazyListState = articleDetailLazyListStates.getValue(
                    uiState.selectedItem.id
                )
            )

            // If we are just showing the detail, have a back press switch to the list.
            // This doesn't take anything more than notifying that we "interacted with the list"
            // since that is what drives the display of the feed
            BackHandler {
                onInteractWithFeed()
            }
        }
    }
}

/**
 * Full-width list items for [PostList]
 *
 * @param items (state) to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun PostListSection(
    items: List<Item>,
    navigateToArticle: (String) -> Unit,
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit
) {
    Column(tabContainerModifier.verticalScroll(rememberScrollState())) {
        items.forEach { post ->
            PostCard(
                item = post,
                navigateToArticle = navigateToArticle,
                isFavorite = favorites.contains(post.id),
                onToggleFavorite = { onToggleFavorite(post.id) }
            )
            PostListDivider()
        }
    }
}

/**
 * Full-width list items for [PostList]
 *
 * @param events (state) to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun EventListSimpleSection(
    events: List<Item>,
    navigateToArticle: (String) -> Unit,
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit
) {
    Column(tabContainerModifier.verticalScroll(rememberScrollState())) {
        events.forEach { event ->
            EventCardSimple(
                item = event,
                navigateToArticle = navigateToArticle,
                isFavorite = favorites.contains(event.id),
                onToggleFavorite = { onToggleFavorite(event.id) }
            )
            PostListDivider()
        }
    }
}

/**
 * A precise enumeration of which type of screen to display at the home route.
 *
 * There are 3 options:
 * - [FeedWithArticleDetails], which displays both a list of all articles and a specific article.
 * - [Feed], which displays just the list of all articles
 * - [ArticleDetails], which displays just a specific article.
 */
private enum class HomeScreenType {
    FeedWithArticleDetails,
    Feed,
    ArticleDetails
}

/**
 * Returns the current [HomeScreenType] to display, based on whether or not the screen is expanded
 * and the [HomeUiState].
 */
@Composable
private fun getHomeScreenType(
    isExpandedScreen: Boolean,
    uiState: HomeUiState
): HomeScreenType = when (isExpandedScreen) {
    false -> {
        when (uiState) {
            is HomeUiState.HasPosts -> {
                if (uiState.isArticleOpen) {
                    HomeScreenType.ArticleDetails
                } else {
                    HomeScreenType.Feed
                }
            }
            is HomeUiState.NoPosts -> HomeScreenType.Feed
        }
    }
    true -> HomeScreenType.FeedWithArticleDetails
}
