package com.sushkpavel.leetcode.presentation.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.submission.SubmissionRequest
import com.sushkpavel.desktopleetcode.domain.model.submission.TestResult
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.usecase.submission.SubmitTaskUseCase
import com.sushkpavel.desktopleetcode.domain.usecase.task.GetTaskUseCase
import com.sushkpavel.leetcode.utils.getToken
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val submitTaskUseCase: SubmitTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {
    private val _screenState = MutableStateFlow(TaskScreenState("", CodeLang.Kotlin))
    val screenState: StateFlow<TaskScreenState> = _screenState.asStateFlow()

    fun onCodeChanged(newCode: String) {
        _screenState.update { it.copy(code = newCode) }
    }

    fun onLanguageChanged(newLanguage: CodeLang) {
        _screenState.update { it.copy(language = newLanguage) }
    }

    fun submitTask() {
        viewModelScope.launch {
            _screenState.update { it.copy(isLoading = true) }
            try {
                val testResult = _screenState.value.task?.id?.let {
                    SubmissionRequest(
                        taskId = it.toInt(),
                        code = _screenState.value.code,
                        language = _screenState.value.language.name.lowercase()
                    )
                }?.let {
                    submitTaskUseCase(
                        it,
                        getToken()
                    )
                }
                when (testResult) {
                    is ApiResult.Error -> {
                        println("error $testResult")
                        _screenState.update {
                            it.copy(error = (testResult.message as NotifyMessage).message)
                        }
                    }

                    ApiResult.NetworkError -> {
                        println("network error $testResult")
                        _screenState.update {
                            it.copy(error = "Achtung! Error")
                        }
                    }

                    is ApiResult.Success<TestResult> -> _screenState.update {
                        it.copy(testResult = testResult.data)
                    }

                    null -> Unit
                }
            } catch (e: Exception) {
                _screenState.update { it.copy(error = e.message) }
            } finally {
                _screenState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onGetTask(difficulty: Difficulty) {
        viewModelScope.launch {
            when (val result = getTaskUseCase(difficulty)) {
                is ApiResult.Success -> {
                    val task = result.data
                    _screenState.value = task?.let {
                        _screenState.value.copy(
                            task = it
                        )
                    }!!
                }

                is ApiResult.Error -> {
                    val errorMessage =
                        (result.message as? NotifyMessage)?.message ?: "Unknown error"
                    _screenState.value = _screenState.value.copy(error = errorMessage)
                }

                ApiResult.NetworkError -> {
                    _screenState.value = _screenState.value.copy(error = "Network error")
                }
            }
        }
    }
}