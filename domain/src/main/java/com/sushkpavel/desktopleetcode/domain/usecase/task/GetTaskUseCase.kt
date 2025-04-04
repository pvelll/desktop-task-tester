package com.sushkpavel.desktopleetcode.domain.usecase.task

import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository

class GetTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(difficulty: Difficulty) = taskRepository.getTask(difficulty)
}