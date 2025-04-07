package com.sushkpavel.leetcode.di

import com.sushkpavel.leetcode.presentation.screens.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
}