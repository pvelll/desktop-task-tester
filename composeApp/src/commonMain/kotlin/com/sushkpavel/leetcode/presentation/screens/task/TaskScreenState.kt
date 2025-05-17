package com.sushkpavel.leetcode.presentation.screens.task

import com.sushkpavel.desktopleetcode.domain.model.submission.TestResult
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.serialization.Serializable
import com.sushkpavel.desktopleetcode.domain.model.task.Task
import java.time.Instant

@Serializable
data class TaskScreenState(
    val code: String = "",
    val language: CodeLang = CodeLang.CPP,
    val isLoading: Boolean = false,
    val error: String? = null,
    val task : Task? = null,
    val testResult: TestResult? = null
)
