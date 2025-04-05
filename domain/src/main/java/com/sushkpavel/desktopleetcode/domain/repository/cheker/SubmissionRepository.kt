package com.sushkpavel.desktopleetcode.domain.repository.cheker

import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.submission.SubmissionRequest
import com.sushkpavel.desktopleetcode.domain.model.submission.TestResult

interface SubmissionRepository {
    suspend fun submitSolution(submissionRequest: SubmissionRequest) : ApiResult<TestResult>
}