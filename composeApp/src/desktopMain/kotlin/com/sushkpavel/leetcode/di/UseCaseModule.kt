package com.sushkpavel.leetcode.di

import com.sushkpavel.desktopleetcode.domain.usecase.user.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single{
        LoginUseCase(get())
    }
}