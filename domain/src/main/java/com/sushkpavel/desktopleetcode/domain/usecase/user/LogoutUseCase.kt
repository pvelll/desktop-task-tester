package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository


class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(tokenValue: String) = userRepository.logout(tokenValue)

}