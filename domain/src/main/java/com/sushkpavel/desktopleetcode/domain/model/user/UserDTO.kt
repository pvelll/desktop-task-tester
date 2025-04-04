package com.sushkpavel.desktopleetcode.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val username: String,
    val email: String,
    val passwordHash: String,
    val role: Role? = null,
)
