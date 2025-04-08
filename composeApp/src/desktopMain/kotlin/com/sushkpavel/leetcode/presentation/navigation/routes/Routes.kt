package com.sushkpavel.leetcode.presentation.navigation.routes

import kotlinx.serialization.Serializable


sealed class Routes {
    @Serializable data object ScreenLogin : Routes()
    @Serializable data object ScreenRegistration : Routes()
}