package com.sushkpavel.leetcode.di

import com.sushkpavel.leetcode.presentation.screens.login.LoginViewModel
import com.sushkpavel.leetcode.presentation.screens.registration.RegistrationViewModel
import com.sushkpavel.leetcode.presentation.screens.task.TaskViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
    viewModel<RegistrationViewModel> {
        RegistrationViewModel(get())
    }
    viewModel<TaskViewModel>{
        TaskViewModel(
            get(),get()
        )
    }
}