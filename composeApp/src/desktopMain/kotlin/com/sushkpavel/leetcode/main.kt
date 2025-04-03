package com.sushkpavel.leetcode

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sushkpavel.desktopleetcode.data.dataModule
import com.sushkpavel.desktopleetcode.data.user.repository.UserRepositoryImpl
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository
import com.sushkpavel.leetcode.presentation.App
import desktopleetcode.composeapp.generated.resources.Res
import desktopleetcode.composeapp.generated.resources.app_name
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() = application {
    startKoin {
        modules(dataModule)
    }
    val userRepository by inject<UserRepository>(UserRepository::class.java)
    CoroutineScope(Dispatchers.IO).launch {
        val rep = userRepository.login(
            Credentials(
                email = "admin@example.com",
                passwordHash = "hashedpassword123"
            )
        )
        println(rep)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}