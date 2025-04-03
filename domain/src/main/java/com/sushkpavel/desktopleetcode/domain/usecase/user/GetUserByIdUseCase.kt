package com.sushkpavel.desktopleetcode.domain.usecase.user

import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository

class GetUserByIdUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(id: Int) = userRepository.getById(id)

}