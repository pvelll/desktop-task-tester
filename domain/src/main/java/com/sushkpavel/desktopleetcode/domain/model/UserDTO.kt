package com.sushkpavel.desktopleetcode.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val username: String,
    val email: String,
    val passwordHash: String,
    val role: Role? = null,
)