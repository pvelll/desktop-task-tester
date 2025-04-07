package com.sushkpavel.leetcode

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sushkpavel.desktopleetcode.data.dataModule
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository
import com.sushkpavel.leetcode.di.useCaseModule
import com.sushkpavel.leetcode.di.viewModelModule
import com.sushkpavel.leetcode.presentation.App
import com.wakaztahir.codeeditor.model.CodeLang
import desktopleetcode.composeapp.generated.resources.Res
import desktopleetcode.composeapp.generated.resources.app_name
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() = application {
    startKoin {
        modules(dataModule, useCaseModule, viewModelModule)
    }


    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}

