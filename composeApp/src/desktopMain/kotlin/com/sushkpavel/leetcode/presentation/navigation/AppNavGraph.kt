package com.sushkpavel.leetcode.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sushkpavel.leetcode.presentation.navigation.routes.Routes
import com.sushkpavel.leetcode.presentation.screens.login.LoginScreen
import com.sushkpavel.leetcode.presentation.screens.registration.RegistrationScreen
import com.sushkpavel.leetcode.presentation.screens.task.TaskScreen

@Composable
fun AppNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.ScreenLogin,
        modifier = Modifier.padding(paddingValues),
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) }
    ) {
        composable<Routes.ScreenLogin>(
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            LoginScreen(navHostController = navController)
        }

        composable<Routes.ScreenRegistration>(
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            RegistrationScreen(navHostController = navController)
        }
        composable<Routes.TaskScreen>(
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            }
        ) {
            TaskScreen(navHostController = navController)
        }
    }
}