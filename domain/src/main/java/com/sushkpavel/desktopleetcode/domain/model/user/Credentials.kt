package com.sushkpavel.desktopleetcode.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val email : String,
    val passwordHash: String
)