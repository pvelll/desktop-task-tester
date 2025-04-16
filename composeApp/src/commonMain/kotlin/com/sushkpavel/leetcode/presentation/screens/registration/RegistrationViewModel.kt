package com.sushkpavel.leetcode.presentation.screens.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.NotifyMessage
import com.sushkpavel.desktopleetcode.domain.model.user.Role
import com.sushkpavel.desktopleetcode.domain.model.user.UserDTO
import com.sushkpavel.desktopleetcode.domain.usecase.user.RegisterUseCase
import com.sushkpavel.leetcode.presentation.util.hash.sha256
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val _screenState = mutableStateOf(
        RegistrationScreenState(
            email = "",
            password = "",
            errorMessage = "",
            username = ""
        )
    )
    val screenState: State<RegistrationScreenState> = _screenState

    fun onEmailChanged(email: String) {
        _screenState.value = _screenState.value.copy(
            email = email,
            errorMessage = ""
        )
    }
    fun onUsernameCahnged(username : String){
        _screenState.value = _screenState.value.copy(
            username = username,
            errorMessage = ""
        )
    }

    fun onPasswordChanged(password: String) {
        _screenState.value = _screenState.value.copy(
            password = password,
            errorMessage = ""
        )
    }

    fun onRegisterClicked(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = registerUseCase(
                UserDTO(
                    email = _screenState.value.email,
                    passwordHash = sha256(_screenState.value.password),
                    username = _screenState.value.username,
                    role = Role.USER
                )
            )
            when (result) {
                is ApiResult.Success -> {
                    onSuccess()
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