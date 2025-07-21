package com.sushkpavel.desktopleetcode.data

import com.sushkpavel.desktopleetcode.data.subbmission.repository.SubmissionRepositoryImpl
import com.sushkpavel.desktopleetcode.data.tasks.repository.TaskRepositoryImpl
import com.sushkpavel.desktopleetcode.data.user.repository.UserRepositoryImpl
import com.sushkpavel.desktopleetcode.domain.repository.cheker.SubmissionRepository
import com.sushkpavel.desktopleetcode.domain.repository.task.TaskRepository
import com.sushkpavel.desktopleetcode.domain.repository.user.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

val dataModule = module {
    single<HttpClient> {
        HttpClient(CIO) {
            engine {
                https {
                    trustManager = object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
                        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) { }
                        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
                    }
                }
            } // temp, needed to be delete
            install(ContentNegotiation) {
                json(Json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 61000L
            }
        }
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }
    single<SubmissionRepository> {
        SubmissionRepositoryImpl(get())
    }
}
