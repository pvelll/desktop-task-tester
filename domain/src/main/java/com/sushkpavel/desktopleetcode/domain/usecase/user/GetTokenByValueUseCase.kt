package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.model.user.Token
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class GetTokenByValueUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(token: String): Token? {
        return userRepository.getTokenByValue(token)
    }
}