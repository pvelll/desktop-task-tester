package com.sushkpavel.desktopleetcode.data

import com.sushkpavel.desktopleetcode.data.user.repository.UserRepositoryImpl
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module{
    single<HttpClient>{
        HttpClient{
            CIO
            install(ContentNegotiation){
                json(Json)
            }
        }
    }
    single<UserRepository>{
        UserRepositoryImpl(get())
    }

}