package com.sushkpavel.desktopleetcode.domain.model.submission

import com.sushkpavel.desktopleetcode.domain.utils.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class TestResult(
    val id: Int,
    val userId: Int,
    val submissionId: Int,
    val taskId: Int,
    val actualResult: String,
    val success: Boolean,
    @Serializable(with = InstantSerializer::class)
    val createdAt : Instant
)