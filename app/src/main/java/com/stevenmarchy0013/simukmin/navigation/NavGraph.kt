package com.stevenmarchy0013.simukmin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stevenmarchy0013.simukmin.screen.MainScreen
import com.stevenmarchy0013.simukmin.screen.DetailScreen

@Composable
fun SetupNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            MainScreen(navController)
        }

        composable(
            route = Screen.Detail.route
        ) {
            val id = it.arguments?.getString("id")?.toLong() ?:-1

            DetailScreen(
                navController = navController,
                id = id
            )
        }
    }
}