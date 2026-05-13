package com.stevenmarchy0013.simukmin.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainScreen")
    data object Detail : Screen("detailScreen/{id}") {
        fun createRoute(id: Long) = "detailScreen/$id"
    }
    data object RecycleBin : Screen("recycleBin")

}