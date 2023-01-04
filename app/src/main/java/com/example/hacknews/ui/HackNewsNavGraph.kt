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

package com.example.hacknews.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hacknews.data.AppContainer
import com.example.hacknews.ui.home.HomeRoute
import com.example.hacknews.ui.home.HomeViewModel
import com.example.hacknews.ui.interests.InterestsRoute
import com.example.hacknews.ui.interests.InterestsViewModel

@Composable
fun HackNewsNavGraph(
    lifecycle: Lifecycle,
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    onClickSearch: () -> Unit = {},
    startDestination: String = HacknewsDestinations.HOME_ROUTE
) {
    val interestsViewModel: InterestsViewModel = viewModel(
        factory = InterestsViewModel.provideFactory(appContainer.interestsRepository)
    )
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(HacknewsDestinations.HOME_ROUTE) {
            val context = LocalContext.current
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(
                    appContainer.postsRepository,
                    appContainer.eventsRepository,
                    context
                )
            )
            lifecycle.addObserver(homeViewModel)
            HomeRoute(
                homeViewModel = homeViewModel,
                interestsViewModel = interestsViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer,
                onClickSearchIcon = onClickSearch
            )
        }
        composable(HacknewsDestinations.INTERESTS_ROUTE) {
            InterestsRoute(
                interestsViewModel = interestsViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}
