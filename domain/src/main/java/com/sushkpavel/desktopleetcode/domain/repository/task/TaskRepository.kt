package com.sushkpavel.desktopleetcode.domain.repository.task

import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.model.task.Task
import com.sushkpavel.desktopleetcode.domain.model.task.TaskDTO

interface TaskRepository {
    suspend fun createTask(taskDTO: TaskDTO): ApiResult<Task>
    suspend fun deleteTask(taskId: Long): ApiResult<NotifyMessage>
    suspend fun updateTask(taskDTO: TaskDTO): ApiResult<Task>
    suspend fun getTask(difficulty: Difficulty): ApiResult<Task?>
}