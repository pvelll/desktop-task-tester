package com.sushkpavel.desktopleetcode.data.tasks.repository

import com.sushkpavel.desktopleetcode.data.repository.NetworkRepository
import com.sushkpavel.desktopleetcode.data.tasks.TaskApiGateway
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.model.task.Task
import com.sushkpavel.desktopleetcode.domain.model.task.TaskDTO
import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod

class TaskRepositoryImpl(client: HttpClient) : NetworkRepository(client), TaskRepository {
    override suspend fun getTask(difficulty: Difficulty, current : Long): ApiResult<Task?> {
        return executeRequest<Task, NotifyMessage>(
            method = HttpMethod.Get,
            url = TaskApiGateway.TASK,
            configureRequest = {
                parameter("difficulty", difficulty.name)
                parameter("current", current)
            }
        )
    }

    override suspend fun createTask(taskDTO: TaskDTO): ApiResult<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: Long): ApiResult<NotifyMessage> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(taskDTO: TaskDTO): ApiResult<Task> {
        TODO("Not yet implemented")
    }

}