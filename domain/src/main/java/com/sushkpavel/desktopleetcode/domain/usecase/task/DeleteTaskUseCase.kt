package com.sushkpavel.desktopleetcode.domain.usecase.task

import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskId: Long) = taskRepository.deleteTask(taskId)
}