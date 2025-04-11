package com.sushkpavel.leetcode.presentation.screens.task

import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.serialization.Serializable

@Serializable
data class TaskScreenState(
    val code: String,
    val language: CodeLang,
    val isLoading: Boolean = false,
    val error: String? = null
)
