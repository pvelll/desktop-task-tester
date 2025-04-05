package com.sushkpavel.desktopleetcode.data.subbmission.repository

import com.sushkpavel.desktopleetcode.data.repository.NetworkRepository
import com.sushkpavel.desktopleetcode.data.subbmission.SubmissionApiGateway
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.submission.SubmissionRequest
import com.sushkpavel.desktopleetcode.domain.model.submission.TestResult
import com.sushkpavel.desktopleetcode.domain.repository.cheker.SubmissionRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType


class SubmissionRepositoryImpl(override val client : HttpClient) : NetworkRepository(client), SubmissionRepository {
    override suspend fun submitSolution(submissionRequest: SubmissionRequest, token :String): ApiResult<TestResult> {
        return executeRequest<TestResult, NotifyMessage>(
            method= HttpMethod.Post,
            url = SubmissionApiGateway.SUBMIT,
            configureRequest ={
                contentType(ContentType.Application.Json)
                setBody(submissionRequest)
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
        )
    }
}