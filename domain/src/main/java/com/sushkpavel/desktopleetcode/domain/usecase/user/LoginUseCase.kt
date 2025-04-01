package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.model.user.Token
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(credentials: Credentials): ApiResult<Token> {
        return userRepository.login(credentials)
    }
}
