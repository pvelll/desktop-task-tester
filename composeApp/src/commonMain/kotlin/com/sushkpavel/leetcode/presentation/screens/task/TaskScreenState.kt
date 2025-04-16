package com.sushkpavel.leetcode.presentation.screens.task

import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.serialization.Serializable
import com.sushkpavel.desktopleetcode.domain.model.task.Task
import java.time.Instant

@Serializable
data class TaskScreenState(
    val code: String,
    val language: CodeLang,
    val isLoading: Boolean = false,
    val error: String? = null,
    val task : Task = Task(
        id = 0,
        title = "Choose the difficulty in tasks",
        description = "then write the code of task",
        examples = "to check solution -> tap submit",
        createdAt = Instant.now(),
        updatedAt = Instant.now(),
        difficulty = Difficulty.HARD,
    ),

)
