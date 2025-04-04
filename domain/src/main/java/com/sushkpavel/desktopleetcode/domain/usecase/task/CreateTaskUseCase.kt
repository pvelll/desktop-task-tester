package com.sushkpavel.desktopleetcode.domain.usecase.task

import com.sushkpavel.desktopleetcode.domain.model.task.TaskDTO
import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository

class CreateTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskDto : TaskDTO) = taskRepository.createTask(taskDto)
}