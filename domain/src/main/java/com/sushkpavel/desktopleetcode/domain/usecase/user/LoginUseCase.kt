package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(credentials: Credentials) = userRepository.login(credentials)

}
