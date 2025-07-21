package com.sushkpavel.leetcode.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.usecase.user.LoginUseCase
import com.sushkpavel.leetcode.presentation.util.hash.sha256
import com.sushkpavel.leetcode.utils.saveToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun onLoginClicked(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginUseCase(
                Credentials(
                    _screenState.value.email,
                    sha256(_screenState.value.password)
                )
            )
            when (result) {
                is ApiResult.Success -> {
                    saveToken(result.data.token)
                    withContext(Dispatchers.Main.immediate){
                        onSuccess()
                    }
                }
                is ApiResult.Error -> {
                    val errorMessage = (result.message as? NotifyMessage)?.message ?: "Unknown error"
                    _screenState.value = _screenState.value.copy(errorMessage = errorMessage)
                }
                is ApiResult.NetworkError -> {
                    _screenState.value = _screenState.value.copy(errorMessage = "Network error")
                }
            }
        }
    }


}

