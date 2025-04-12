package com.sushkpavel.leetcode.di

import com.sushkpavel.desktopleetcode.domain.usecase.submission.SubmitTaskUseCase
import com.sushkpavel.desktopleetcode.domain.usecase.task.GetTaskUseCase
import com.sushkpavel.desktopleetcode.domain.usecase.user.LoginUseCase
import com.sushkpavel.desktopleetcode.domain.usecase.user.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single{
        LoginUseCase(get())
    }
    single{
        RegisterUseCase(get())
    }
    single{
        SubmitTaskUseCase(get())
    }
    single{
        GetTaskUseCase(get())
    }
}