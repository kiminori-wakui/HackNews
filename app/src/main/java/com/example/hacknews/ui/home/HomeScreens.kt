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
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hacknews.R
import com.example.hacknews.data.Result
import com.example.hacknews.data.posts.impl.BlockingFakePostsRepository
import com.example.hacknews.model.Item
import com.example.hacknews.ui.article.postContentItems
import com.example.hacknews.ui.article.sharePost
import com.example.hacknews.ui.components.HacknewsSnackbarHost
import com.example.hacknews.ui.modifiers.interceptKey
import com.example.hacknews.ui.rememberContentPaddingForScreen
import com.example.hacknews.ui.theme.HacknewsTheme
import com.example.hacknews.ui.utils.BookmarkButton
import com.example.hacknews.ui.utils.FavoriteButton
import com.example.hacknews.ui.utils.ShareButton
import com.example.hacknews.ui.utils.TextSettingsButton
import com.example.hacknews.utils.isScrolled
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

/**
 * The home screen displaying the feed along with an article details.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeFeedWithArticleDetailsScreen(
    uiState: HomeUiState,
    homeTabContent: List<HomeTabContent>,
    showTopAppBar: Boolean,
    onToggleFavorite: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    onInteractWithList: () -> Unit,
    onInteractWithDetail: (String) -> Unit,
    openDrawer: () -> Unit,
    onClickSearch: () -> Unit,
    homeListLazyListState: LazyListState,
    articleDetailLazyListStates: Map<String, LazyListState>,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    onSearchInputChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {
    HomeScreenWithList(
        uiState = uiState,
        showTopAppBar = showTopAppBar,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        openDrawer = openDrawer,
        onClickSearch = onClickSearch,
        homeListLazyListState = homeListLazyListState,
        scaffoldState = scaffoldState,
        modifier = modifier,
    ) { hasPostsUiState, contentModifier ->
        val contentPadding = rememberContentPaddingForScreen(additionalTop = 8.dp)
        Row(contentModifier) {
            PostList(
                homeTabContent = homeTabContent,
                showExpandedSearch = !showTopAppBar,
                modifier = Modifier
                    .width(334.dp)
                    .notifyInput(onInteractWithList)
                    .imePadding(),
                searchInput = hasPostsUiState.searchInput,
                onSearchInputChanged = onSearchInputChanged,
                onSearch = onSearch,
            )
            // Crossfade between different detail posts
            Crossfade(targetState = hasPostsUiState.selectedItem) { detailPost ->
                // Get the lazy list state for this detail view
                val detailLazyListState by derivedStateOf {
                    articleDetailLazyListStates.getValue(detailPost.id)
                }

                // Key against the post id to avoid sharing any state between different posts
                key(detailPost.id) {
                    LazyColumn(
                        state = detailLazyListState,
                        contentPadding = contentPadding,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                            .notifyInput {
                                onInteractWithDetail(detailPost.id)
                            }
                            .imePadding() // add padding for the on-screen keyboard
                    ) {
                        stickyHeader {
                            val context = LocalContext.current
                            PostTopBar(
                                isFavorite = hasPostsUiState.favorites.contains(detailPost.id),
                                onToggleFavorite = { onToggleFavorite(detailPost.id) },
                                onSharePost = { sharePost(detailPost, context) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                            )
                        }
                        postContentItems(detailPost)
                    }
                }
            }
        }
    }
}

/**
 * A [Modifier] that tracks all input, and calls [block] every time input is received.
 */
private fun Modifier.notifyInput(block: () -> Unit): Modifier =
    composed {
        val blockState = rememberUpdatedState(block)
        pointerInput(Unit) {
            while (currentCoroutineContext().isActive) {
                awaitPointerEventScope {
                    awaitPointerEvent(PointerEventPass.Initial)
                    blockState.value()
                }
            }
        }
    }

/**
 * The home screen displaying just the article feed.
 */
@Composable
fun HomeFeedScreen(
    uiState: HomeUiState,
    homeTabContent: List<HomeTabContent>,
    showTopAppBar: Boolean,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    onClickSearch: () -> Unit,
    onSearch: () -> Unit,
    homeListLazyListState: LazyListState,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
) {
    HomeScreenWithList(
        uiState = uiState,
        showTopAppBar = showTopAppBar,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        openDrawer = openDrawer,
        onClickSearch = onClickSearch,
        homeListLazyListState = homeListLazyListState,
        scaffoldState = scaffoldState,
        modifier = modifier
    ) { _, contentModifier ->
        PostList(
            homeTabContent = homeTabContent,
            showExpandedSearch = !showTopAppBar,
            modifier = contentModifier,
            searchInput = searchInput,
            onSearchInputChanged = onSearchInputChanged,
            onSearch = onSearch
        )
    }
}

/**
 * A display of the home screen that has the list.
 *
 * This sets up the scaffold with the top app bar, and surrounds the [hasPostsContent] with refresh,
 * loading and error handling.
 *
 * This helper functions exists because [HomeFeedWithArticleDetailsScreen] and [HomeFeedScreen] are
 * extremely similar, except for the rendered content when there are posts to display.
 */
@Composable
private fun HomeScreenWithList(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    onClickSearch: () -> Unit,
    homeListLazyListState: LazyListState,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    hasPostsContent: @Composable (
        uiState: HomeUiState.HasPosts,
        modifier: Modifier
    ) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { HacknewsSnackbarHost(hostState = it) },
        topBar = {
            if (showTopAppBar) {
                HomeTopAppBar(
                    openDrawer = openDrawer,
                    onClickSearch = onClickSearch,
                    elevation = if (!homeListLazyListState.isScrolled) 0.dp else 4.dp
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        LoadingContent(
            empty = when (uiState) {
                is HomeUiState.HasPosts -> false
                is HomeUiState.NoPosts -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            loading = uiState.isLoading,
            onRefresh = onRefreshPosts,
            content = {
                when (uiState) {
                    is HomeUiState.HasPosts -> hasPostsContent(uiState, contentModifier)
                    is HomeUiState.NoPosts -> {
                        if (uiState.errorMessages.isEmpty()) {
                            // if there are no posts, and no error, let the user refresh manually
                            TextButton(
                                onClick = onRefreshPosts,
                                modifier.fillMaxSize()
                            ) {
                                Text(
                                    stringResource(id = R.string.home_tap_to_load_content),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            // there's currently an error showing, don't show any content
                            Box(contentModifier.fillMaxSize()) { /* empty screen */ }
                        }
                    }
                }
            }
        )
    }

    BackHandler {
        if (!showTopAppBar) onClickSearch()
    }
    // Process one error message at a time and show them as Snackbars in the UI
    if (uiState.errorMessages.isNotEmpty()) {
        // Remember the errorMessage to display on the screen
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }

        // Get the text to show on the message from resources
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryMessageText = stringResource(id = R.string.retry)

        // If onRefreshPosts or onErrorDismiss change while the LaunchedEffect is running,
        // don't restart the effect and use the latest lambda values.
        val onRefreshPostsState by rememberUpdatedState(onRefreshPosts)
        val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

        // Effect running in a coroutine that displays the Snackbar on the screen
        // If there's a change to errorMessageText, retryMessageText or scaffoldState,
        // the previous effect will be cancelled and a new one will start with the new values
        LaunchedEffect(errorMessageText, retryMessageText, scaffoldState) {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryMessageText
            )
            if (snackbarResult == SnackbarResult.ActionPerformed) {
                onRefreshPostsState()
            }
            // Once the message is displayed and dismissed, notify the ViewModel
            onErrorDismissState(errorMessage.id)
        }
    }
}

/**
 * Display an initial empty state or swipe to refresh content.
 *
 * @param empty (state) when true, display [emptyContent]
 * @param emptyContent (slot) the content to display for the empty state
 * @param loading (state) when true, display a loading spinner over [content]
 * @param onRefresh (event) event to request refresh
 * @param content (slot) the main content to show
 */
@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(loading),
            onRefresh = onRefresh,
            content = content,
        )
    }
}

/**
 * Display a feed of posts.
 **
 * @param modifier modifier for the root element
 */
@Composable
private fun PostList(
    homeTabContent: List<HomeTabContent>,
    showExpandedSearch: Boolean,
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {
    val (currentSection, updateSection) = rememberSaveable {
        mutableStateOf(homeTabContent.first().section)
    }
    val selectedTabIndex = homeTabContent.indexOfFirst { it.section == currentSection }
    Column(modifier) {
        if (showExpandedSearch) {
            HomeSearch(
                Modifier.padding(horizontal = 16.dp),
                searchInput = searchInput,
                onSearchInputChanged = onSearchInputChanged,
                onSearch = onSearch
            )
        }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = colors.onPrimary,
            contentColor = colors.primary
        ) {
            HomeTabRowContent(selectedTabIndex, updateSection, homeTabContent)
        }
        Divider(
            color = colors.onSurface.copy(alpha = 0.1f)
        )
        Box(modifier = Modifier.weight(1f)) {
            // display the current tab content which is a @Composable () -> Unit
            homeTabContent[selectedTabIndex].content()
        }
//        if (postsFeed.popularPosts.isNotEmpty() && !showExpandedSearch) {
//            item {
//                PostListPopularSection(
//                    postsFeed.popularPosts, onArticleTapped
//                )
//            }
//        }
//        if (postsFeed.recentPosts.isNotEmpty()) {
//            item { PostListHistorySection(postsFeed.recentPosts, onArticleTapped) }
//        }
    }
}

@Composable
fun HomeTabRowContent(
    selectedTabIndex: Int,
    updateSection: (HomeSections) -> Unit,
    tabContent: List<HomeTabContent>,
    modifier: Modifier = Modifier
) {
    tabContent.forEachIndexed { index, content ->
        val colorText = if (selectedTabIndex == index) {
            colors.primary
        } else {
            colors.onSurface.copy(alpha = 0.8f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = { updateSection(content.section) },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(id = content.section.titleResId),
                color = colorText,
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}

/**
 * Full screen circular progress indicator
 */
@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

/**
 * Top section of [PostList]
 *
 * @param item (state) highlighted post to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun PostListTopSection(item: Item, navigateToArticle: (String) -> Unit) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        text = stringResource(id = R.string.home_hackers_post_section_title),
        style = MaterialTheme.typography.subtitle1
    )
//    PostCardTop(
//        post = post,
//        modifier = Modifier.clickable(onClick = { navigateToArticle(post.id) })
//    )
    PostListDivider()
}

/**
 * Horizontal scrolling cards for [PostList]
 *
 * @param posts (state) to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun PostListPopularSection(
    posts: List<Item>,
    navigateToArticle: (String) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.home_popular_section_title),
            style = MaterialTheme.typography.subtitle1
        )

//        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
//            items(posts) { post ->
//                PostCardPopular(
//                    post,
//                    navigateToArticle,
//                    Modifier.padding(start = 16.dp, bottom = 16.dp)
//                )
//            }
//        }
        PostListDivider()
    }
}

/**
 * Full-width list items that display "based on your history" for [PostList]
 *
 * @param items (state) to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun PostListHistorySection(
    items: List<Item>,
    navigateToArticle: (String) -> Unit
) {
    Column {
        items.forEach { post ->
            PostCardHistory(post, navigateToArticle)
            PostListDivider()
        }
    }
}

/**
 * Full-width divider with padding for [PostList]
 */
@Composable
fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = colors.onSurface.copy(alpha = 0.08f)
    )
}

/**
 * Expanded search UI - includes support for enter-to-send and escape-to-dismiss on the search field
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HomeSearch(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, colors.onSurface.copy(alpha = .6f)),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(onClick = {
                    // TODO: ????????????????????????????????????
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.cd_search)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                val context = LocalContext.current
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = searchInput,
                    onValueChange = { onSearchInputChanged(it) },
                    placeholder = { Text(stringResource(R.string.home_search)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), // keyboardOptions change the newline key to a search key on the soft keyboard
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    // keyboardActions submits the search query when the search key is pressed
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            submitSearch(onSearch, context)
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .interceptKey(Key.Enter) { // submit a search query when Enter is pressed
                            submitSearch(onSearch, context)
                        }
                        .interceptKey(Key.Escape) { // dismiss focus when Escape is pressed
                            focusManager.clearFocus()
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    // TODO: ????????????????????????????????????
                }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = stringResource(R.string.cd_more_actions)
                    )
                }
            }
        }
    }
}

/**
 * Stub helper function to submit a user's search query
 */
private fun submitSearch(
    onSearch: () -> Unit,
    context: Context
) {
    onSearch()
    Toast.makeText(
        context,
        "Search is not yet implemented",
        Toast.LENGTH_SHORT
    ).show()
}

/**
 * Top bar for a Post when displayed next to the Home feed
 */
@Composable
private fun PostTopBar(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSharePost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, colors.onSurface.copy(alpha = .6f)),
        modifier = modifier.padding(end = 16.dp)
    ) {
        Row(Modifier.padding(horizontal = 8.dp)) {
            FavoriteButton(onClick = { /* Functionality not available */ })
            BookmarkButton(isBookmarked = isFavorite, onClick = onToggleFavorite)
            ShareButton(onClick = onSharePost)
            TextSettingsButton(onClick = { /* Functionality not available */ })
        }
    }
}

/**
 * TopAppBar for the Home screen
 */
@Composable
private fun HomeTopAppBar(
    elevation: Dp,
    openDrawer: () -> Unit,
    onClickSearch: () -> Unit
) {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.ic_hacknews_wordmark),
                contentDescription = title,
                tint = colors.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(R.drawable.ic_hacknews_logo),
                    contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                    tint = colors.primary
                )
            }
        },
        actions = {
            IconButton(onClick = {
            /* TODO: Open search */
                onClickSearch()
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.cd_search)
                )
            }
        },
        backgroundColor = colors.surface,
        elevation = elevation
    )
}

@Preview("Home list drawer screen")
@Preview("Home list drawer screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Home list drawer screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewHomeListDrawerScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    HacknewsTheme {
        HomeFeedScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedItem = postsFeed.highlightedItem,
                isArticleOpen = false,
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            ),
            homeTabContent = listOf(),
            showTopAppBar = false,
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            onClickSearch = {},
            onSearch = {},
            homeListLazyListState = rememberLazyListState(),
            scaffoldState = rememberScaffoldState(),
            onSearchInputChanged = {},
        )
    }
}

@Preview("Home list navrail screen", device = Devices.NEXUS_7_2013)
@Preview(
    "Home list navrail screen (dark)",
    uiMode = UI_MODE_NIGHT_YES,
    device = Devices.NEXUS_7_2013
)
@Preview("Home list navrail screen (big font)", fontScale = 1.5f, device = Devices.NEXUS_7_2013)
@Composable
fun PreviewHomeListNavRailScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    HacknewsTheme {
        HomeFeedScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedItem = postsFeed.highlightedItem,
                isArticleOpen = false,
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            ),
            homeTabContent = listOf(),
            showTopAppBar = true,
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            onClickSearch = {},
            onSearch = {},
            homeListLazyListState = rememberLazyListState(),
            scaffoldState = rememberScaffoldState(),
            onSearchInputChanged = {},
        )
    }
}

@Preview("Home list detail screen", device = Devices.PIXEL_C)
@Preview("Home list detail screen (dark)", uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Home list detail screen (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewHomeListDetailScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    HacknewsTheme {
        HomeFeedWithArticleDetailsScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedItem = postsFeed.highlightedItem,
                isArticleOpen = false,
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            ),
            homeTabContent = listOf(),
            showTopAppBar = true,
            onToggleFavorite = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            onInteractWithList = {},
            onInteractWithDetail = {},
            openDrawer = {},
            onClickSearch = {},
            homeListLazyListState = rememberLazyListState(),
            articleDetailLazyListStates = postsFeed.allItems.associate { post ->
                key(post.id) {
                    post.id to rememberLazyListState()
                }
            },
            scaffoldState = rememberScaffoldState(),
            onSearchInputChanged = {},
            onSearch = {},
        )
    }
}
