package com.sushkpavel.desktopleetcode.domain.model;


import kotlinx.serialization.Serializable;

@Serializable
data class NotifyMessage(
    val message: String,
    val code: Int
)