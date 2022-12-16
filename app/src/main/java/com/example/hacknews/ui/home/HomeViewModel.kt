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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hacknews.R
import com.example.hacknews.data.Result
import com.example.hacknews.data.posts.PostsRepository
import com.example.hacknews.model.Post
import com.example.hacknews.model.PostsFeed
import com.example.hacknews.net.Qiita
import com.example.hacknews.utils.ErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.UUID

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
     * There is guaranteed to be a [selectedPost], which is one of the posts from [postsFeed].
     */
    data class HasPosts(
        val postsFeed: PostsFeed,
        val selectedPost: Post,
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
    val postsFeed: PostsFeed? = null,
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
        if (postsFeed == null) {
            HomeUiState.NoPosts(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.HasPosts(
                postsFeed = postsFeed,
                // Determine the selected post. This will be the post the user last selected.
                // If there is none (or that post isn't in the current feed), default to the
                // highlighted post
                selectedPost = postsFeed.allPosts.find {
                    it.id == selectedPostId
                } ?: postsFeed.highlightedPost,
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
    private val postsRepository: PostsRepository
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

        // TODO: Jsonマッピングの書き方
//        val mapper = jacksonObjectMapper()
//        val color = mapper.readValue(json, Color2::class.java)


        val url = "https://qiita.com/api/v2/items?page=1&query=Twitter%E3%82%92%E6%95%91%E3%81%84%E3%81%9F%E3%81%84%EF%BC%81Flutter%E3%81%A7Twitter%E3%82%AF%E3%83%A9%E3%82%A4%E3%82%A2%E3%83%B3%E3%83%88%E3%82%A2%E3%83%97%E3%83%AA%E3%82%92%E4%BD%9C%E3%81%A3%E3%81%9F"
        // Observe for favorite changes in the repo layer
        viewModelScope.launch {
            weatherBackgroundTask(url)

            postsRepository.observeFavorites().collect { favorites ->
                viewModelState.update { it.copy(favorites = favorites) }
            }
        }
    }

    //３）HTTP通信（ワーカースレッド）の中身(※suspend＝中断する可能性がある関数につける)
    private suspend fun weatherBackgroundTask(weatherUrl:String):String{
        //withContext=スレッドを分離しますよ、Dispatchers.IO＝ワーカースレッド
        val response = withContext(Dispatchers.IO){
            // 天気情報サービスから取得した結果情報（JSON文字列）を後で入れるための変数（いったん空っぽ）を用意。
            var httpResult = ""

            //  try{エラーがあるかもしれない処理を実行}catch{実際エラーがあった場合}
            try{
                //ただのURL文字列をURLオブジェクトに変換（文字列にリンクを付けるイメージ）
                val urlObj = URL(weatherUrl)

                // アクセスしたAPIから情報を取得
                //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
                val br = BufferedReader(InputStreamReader(urlObj.openStream()))
                httpResult = br.readText()

//                val json = """{"rendered_body":"aaa","body":"bbbb","coediting":false,"comments_count":0,"created_at":"2022-12-15T18:09:08+09:00","group":null,"id":"c76a777495aa1c6403a4","likes_count":0,"private":false,"reactions_count":0,"stocks_count":0,"tags":[{"name":"Twitter","versions":[]},{"name":"TwitterAPI","versions":[]},{"name":"開発プロセス","versions":[]},{"name":"Flutter","versions":[]}],"title":"Twitterを救いたい！FlutterでTwitterクライアントアプリを作った","updated_at":"2022-12-15T18:09:08+09:00","url":"https://qiita.com/Hanull/items/c76a777495aa1c6403a4","user":{"description":"アプリ・サービス・ゲーム開発などシステム開発を行うグループ","facebook_id":"","followees_count":1,"followers_count":1,"github_login_name":null,"id":"Hanull","items_count":5,"linkedin_id":"","location":"","name":"Hanull","organization":"Hanull","permanent_id":2727171,"profile_image_url":"https://pbs.twimg.com/profile_images/950048925590810624/iEhYOe_N_bigger.jpg","team_only":false,"twitter_screen_name":"hanulldog","website_url":"https://hanull.jp/"},"page_views_count":null,"team_membership":null}"""
//                val json = """{"rendered_body":"aaa","body":"bbbb","coediting":false,"comments_count":0,"id":"c76a777495aa1c6403a4","likes_count":0,"private":false,"reactions_count":0,"stocks_count":0,"user":{"description":"アプリ・サービス・ゲーム開発などシステム開発を行うグループ","facebook_id":"","followees_count":1,"followers_count":1,"id":"Hanull","items_count":5,"linkedin_id":"","location":"","name":"Hanull","organization":"Hanull","permanent_id":2727171,"profile_image_url":"https://pbs.twimg.com/profile_images/950048925590810624/iEhYOe_N_bigger.jpg","team_only":false,"twitter_screen_name":"hanulldog","website_url":"https://hanull.jp/"},"team_membership":null}"""
//                val color = Json.decodeFromString<Qiita>(string = json)
////                val color = jacksonObjectMapper().readValue(httpResult, Array<Qiita>::class.java)
//                httpResult = color.toString()

                httpResult = br.readText()
            }catch (e: IOException){//IOExceptionとは例外管理するクラス
                e.printStackTrace() //エラーが発生したよって言う
            }catch (e: JSONException){ //JSONデータ構造に問題が発生した場合の例外
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            //HTTP接続の結果、取得したJSON文字列httpResultを戻り値とする
            return@withContext httpResult
        }


        return response
    }

//    data class Color2(val color: String, val kana: String, val code: Code)
//    data class Code(val rgba: Array<Int>, val hex: String)

    /**
     * Refresh posts and update the UI state accordingly
     */
    fun refreshPosts() {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = postsRepository.getPostsFeed()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(postsFeed = result.data, isLoading = false)
                    is Result.Error -> {
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.load_error
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
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
    fun selectArticle(postId: String) {
        // Treat selecting a detail as simply interacting with it
        interactedWithArticleDetails(postId)
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
        fun provideFactory(
            postsRepository: PostsRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(postsRepository) as T
            }
        }
    }
}
