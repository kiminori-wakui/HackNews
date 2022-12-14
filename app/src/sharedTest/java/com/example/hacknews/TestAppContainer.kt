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

package com.example.hacknews

import android.content.Context
import com.example.hacknews.data.AppContainer
import com.example.hacknews.data.events.EventsRepository
import com.example.hacknews.data.events.impl.EventsRepositoryImpl
import com.example.hacknews.data.interests.InterestsRepository
import com.example.hacknews.data.interests.impl.InterestsRepositoryImpl
import com.example.hacknews.data.posts.PostsRepository
import com.example.hacknews.data.posts.impl.BlockingFakePostsRepository

class TestAppContainer(private val context: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        BlockingFakePostsRepository()
    }

    override val eventsRepository: EventsRepository by lazy {
        EventsRepositoryImpl()
    }

    override val interestsRepository: InterestsRepository by lazy {
        InterestsRepositoryImpl()
    }
}
