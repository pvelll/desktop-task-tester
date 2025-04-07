package com.sushkpavel.leetcode.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sushkpavel.leetcode.presentation.navigation.routes.Routes
import com.sushkpavel.leetcode.presentation.screens.login.LoginScreen

@Composable
fun AppNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.ScreenLogin,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Routes.ScreenLogin> {
            LoginScreen(navHostController = navController)
        }
//        composable<Routes.ScreenBookmarks> {
//            BookmarksScreen(navController = navController)
//        }
//        composable<Routes.ScreenNewsDetails>{
//            val args = it.toRoute<Routes.ScreenNewsDetails>()
//            NewsDetailsScreen(news = Json.decodeFromString(News.serializer(), args.news),navController = navController)
//        }
    }
}