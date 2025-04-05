package com.sushkpavel.desktopleetcode.domain.usecase.submission

import com.sushkpavel.desktopleetcode.domain.model.submission.SubmissionRequest
import com.sushkpavel.desktopleetcode.domain.repository.cheker.SubmissionRepository

class SubmitTaskUseCase(private val submissionRepository: SubmissionRepository) {
    suspend operator fun invoke(submissionRequest: SubmissionRequest, token : String ) =
        submissionRepository.submitSolution(submissionRequest, token)
}