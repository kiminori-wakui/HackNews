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

package com.example.hacknews.data.posts

import com.example.hacknews.data.Result
import com.example.hacknews.model.Item
import com.example.hacknews.model.ItemsFeed
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the Posts data layer.
 */
interface PostsRepository {

    /**
     * Get a specific HackNews post.
     */
    suspend fun getPost(postId: String?): Result<Item>

    /**
     * Get HackNews posts.
     */
    suspend fun getPostsFeed(): Result<ItemsFeed>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)
}
