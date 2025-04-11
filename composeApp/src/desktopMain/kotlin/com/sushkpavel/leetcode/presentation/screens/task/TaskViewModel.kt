package com.sushkpavel.leetcode.presentation.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.usecase.submission.SubmitTaskUseCase
import com.wakaztahir.codeeditor.model.CodeLang
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val submitTaskUseCase: SubmitTaskUseCase
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
//                submitTaskUseCase(_screenState.value.code, _screenState.value.language)
                // Handle success
            } catch (e: Exception) {
                _screenState.update { it.copy(error = e.message) }
            } finally {
                _screenState.update { it.copy(isLoading = false) }
            }
        }
    }
}