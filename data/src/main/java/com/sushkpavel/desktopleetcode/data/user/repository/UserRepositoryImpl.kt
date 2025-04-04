package com.sushkpavel.desktopleetcode.data.user.repository

import com.sushkpavel.desktopleetcode.data.repository.NetworkRepository
import com.sushkpavel.desktopleetcode.data.user.UserApiGateway
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.model.user.Token
import com.sushkpavel.desktopleetcode.domain.model.user.User
import com.sushkpavel.desktopleetcode.domain.model.user.UserDTO
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod


class UserRepositoryImpl(override val client: HttpClient) : NetworkRepository(client), UserRepository {
    override suspend fun login(credentials: Credentials): ApiResult<Token> {
        return executeRequest<Token, NotifyMessage>(
            method = HttpMethod.Post,
            url = UserApiGateway.LOGIN,
            configureRequest = { setBody(credentials) }
        )
    }

    override suspend fun logout(tokenValue: String): ApiResult<NotifyMessage> {
        return executeRequest<NotifyMessage, NotifyMessage>(
            method = HttpMethod.Post,
            url = UserApiGateway.LOGOUT,
            configureRequest = {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $tokenValue")
                }
            }
        )

    }

    override suspend fun register(user: UserDTO): ApiResult<NotifyMessage> {
        return executeRequest<NotifyMessage, NotifyMessage>(
            method = HttpMethod.Post,
            url = UserApiGateway.REGISTER,
            configureRequest = {
                setBody(user)
            }
        )
    }

    override suspend fun getById(id: Int): ApiResult<User> {
        return executeRequest<User, NotifyMessage>(
            method = HttpMethod.Get,
            url = UserApiGateway.USERS,
            configureRequest = {
                parameter("id", id)
            }
        )
    }

    override suspend fun update(id: Int, user: User): ApiResult<NotifyMessage> {
        return executeRequest<NotifyMessage, NotifyMessage>(
            method = HttpMethod.Put,
            url = UserApiGateway.USERS,
            configureRequest = {
                parameter("id", id)
                setBody(user)
            }
        )
    }

    override suspend fun getUserByEmail(email: String): ApiResult<User?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int, token : String): ApiResult<NotifyMessage> {
        return executeRequest<NotifyMessage, NotifyMessage>(
            method = HttpMethod.Delete,
            url = UserApiGateway.USERS,
            configureRequest = {
                parameter("id", id)
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
        )
    }
}