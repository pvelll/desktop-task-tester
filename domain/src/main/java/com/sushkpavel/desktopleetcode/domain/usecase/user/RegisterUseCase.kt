package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.model.user.UserDTO
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserDTO) = userRepository.register(user)

}