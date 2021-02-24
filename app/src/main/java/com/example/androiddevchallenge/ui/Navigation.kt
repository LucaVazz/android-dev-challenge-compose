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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.DetailsScreen
import com.example.androiddevchallenge.ui.screens.ListScreen

sealed class Screen(
    val routeDefinition: String
) {
    object List : Screen(routeDefinition = "List")
    object Details : Screen(routeDefinition = "Details?id={id}")
}

@Composable
fun BasicNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.List.routeDefinition) {
        composable(Screen.List.routeDefinition) {
            ListScreen(navController)
        }

        composable(
            Screen.Details.routeDefinition,
            arguments = listOf(navArgument("id") { })
        ) { backStackEntry ->
            DetailsScreen(backStackEntry.arguments?.getString("id"), navController)
        }
    }
}
