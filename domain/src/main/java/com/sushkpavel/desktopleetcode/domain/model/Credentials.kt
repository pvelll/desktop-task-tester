package com.sushkpavel.desktopleetcode.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val email : String,
    val passwordHash: String
)