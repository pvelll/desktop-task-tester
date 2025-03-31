package com.sushkpavel.desktopleetcode.domain.model;


import kotlinx.serialization.Serializable;

@Serializable
data class NotifyMessageDTO(
    val message: String,
    val code: Int
)