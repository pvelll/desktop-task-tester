package com.sushkpavel.leetcode.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.usecase.user.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _screenState = mutableStateOf(
        LoginScreenState(
            email = "",
            password = "",
            errorMessage = ""
        )
    )
    val screenState: State<LoginScreenState> = _screenState

    fun onEmailChanged(email: String) {
        _screenState.value = _screenState.value.copy(
            email = email,
            errorMessage = ""
        )
    }

    fun onPasswordChanged(password: String) {
        _screenState.value = _screenState.value.copy(
            password = password,
            errorMessage = ""
        )
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            val result = loginUseCase(
                Credentials(
                    _screenState.value.email,
                    _screenState.value.password
                )
            )

            if (result is ApiResult.Success) {
                TODO("task screen navigation")
            } else {
                _screenState.value = _screenState.value.copy(
                    errorMessage = "Login failed"
                )
            }
        }
    }
}