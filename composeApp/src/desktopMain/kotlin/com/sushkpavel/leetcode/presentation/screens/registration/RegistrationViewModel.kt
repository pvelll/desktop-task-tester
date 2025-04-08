package com.sushkpavel.leetcode.presentation.screens.registration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushkpavel.desktopleetcode.domain.model.ApiResult
import com.sushkpavel.desktopleetcode.domain.model.user.Credentials
import com.sushkpavel.desktopleetcode.domain.model.user.Role
import com.sushkpavel.desktopleetcode.domain.model.user.UserDTO
import com.sushkpavel.desktopleetcode.domain.usecase.user.RegisterUseCase
import com.sushkpavel.leetcode.presentation.screens.login.LoginScreenState
import com.sushkpavel.leetcode.presentation.util.hash.sha256
import kotlinx.coroutines.launch
import java.security.AlgorithmParametersSpi
import java.security.MessageDigest

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

    fun onPasswordChanged(password: String) {
        _screenState.value = _screenState.value.copy(
            password = password,
            errorMessage = ""
        )
    }

    fun onLoginClicked(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = registerUseCase(
                UserDTO(
                    email =_screenState.value.email,
                    passwordHash = sha256(_screenState.value.password),
                    username = _screenState.value.username,
                    role = Role.USER // TODO implement logic for teachers
                )
            )
            if (result is ApiResult.Success) {
                onSuccess()
            } else {
                _screenState.value = _screenState.value.copy(
                    errorMessage = "Login failed"
                )
            }
        }
    }
}