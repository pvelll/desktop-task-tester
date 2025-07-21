package com.sushkpavel.desktopleetcode.data.repository

import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.HttpStatusCode

abstract class NetworkRepository(open val client: HttpClient) {

    suspend inline fun <reified SuccessType, reified ErrorType : Any> executeRequest(
        url: String,
        method: HttpMethod = HttpMethod.Get,
        configureRequest: HttpRequestBuilder.() -> Unit = {},
        handleSuccess: (SuccessType) -> ApiResult<SuccessType> = { ApiResult.Success(it) },
        handleError: (ErrorType) -> ApiResult.Error = { ApiResult.Error(it) }
    ): ApiResult<SuccessType> {
        return try {
            val response = client.request(url) {
                this.method = method
                contentType(ContentType.Application.Json)
                apply(configureRequest)
            }

            when (response.status) {
                HttpStatusCode.OK -> handleSuccess(response.body())
                else ->
                    try {
                        handleError(response.body())
                    } catch (e: Exception) {
                        ApiResult.Error("Unknown error: ${response.status}")
                    }
            }
        } catch (e: ClientRequestException) {
            ApiResult.Error(e.response.body())
        } catch (e: ServerResponseException) {
            ApiResult.Error(e.response.body())
        } catch (e: Exception) {
            ApiResult.NetworkError
        }
    }
}