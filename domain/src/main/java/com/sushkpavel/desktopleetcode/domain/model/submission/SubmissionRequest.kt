package com.sushkpavel.desktopleetcode.domain.model.submission

import kotlinx.serialization.Serializable

@Serializable
data class SubmissionRequest(
    val taskId: Int,
    val code: String,
    val language: String
)
