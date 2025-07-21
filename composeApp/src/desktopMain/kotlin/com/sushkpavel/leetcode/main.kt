package com.sushkpavel.leetcode

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sushkpavel.leetcode.presentation.App
import desktopleetcode.composeapp.generated.resources.Res
import desktopleetcode.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}


