package com.sushkpavel.desktopleetcode.domain.repository.user

import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.model.user.Token
import com.sushkpavel.desktopleetcode.domain.model.user.User
import com.sushkpavel.desktopleetcode.domain.model.user.UserDTO

interface UserRepository {
    suspend fun login(credentials: Credentials): ApiResult<Token>
    suspend fun logout(tokenValue: String) : ApiResult<NotifyMessage>
    suspend fun register(user: UserDTO): Int?
    suspend fun getById(id: Int): User?
    suspend fun update(id: Int, user: User)
    suspend fun getUserByEmail(email: String) : User?
    suspend fun delete(id: Int)
    suspend fun getTokenByValue(token : String) : Token?
}