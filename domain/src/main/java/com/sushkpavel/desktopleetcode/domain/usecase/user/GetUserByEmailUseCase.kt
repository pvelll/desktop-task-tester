package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.model.user.User
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class GetUserByEmailUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String): User? {
        return userRepository.getUserByEmail(email)
    }
}