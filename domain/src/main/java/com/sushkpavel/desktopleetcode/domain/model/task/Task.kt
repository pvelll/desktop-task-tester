package com.sushkpavel.desktopleetcode.domain.model.task

import com.sushkpavel.desktopleetcode.domain.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Task (
    val id : Long,
    val title : String,
    val description : String,
    val difficulty : Difficulty,
    val examples : String,
    @Serializable(with = InstantSerializer::class)
    val createdAt : Instant,
    @Serializable(with = InstantSerializer::class)
    val updatedAt : Instant,
)