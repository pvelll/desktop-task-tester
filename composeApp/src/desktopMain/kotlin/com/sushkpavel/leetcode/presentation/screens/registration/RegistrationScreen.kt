package com.sushkpavel.leetcode.presentation.screens.registration

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sushkpavel.leetcode.presentation.navigation.routes.Routes
import com.sushkpavel.leetcode.presentation.util.CustomClickableText
import com.sushkpavel.leetcode.presentation.util.CustomTextInputField
import com.sushkpavel.leetcode.presentation.util.EmailField
import com.sushkpavel.leetcode.presentation.util.ErrorMessage
import com.sushkpavel.leetcode.presentation.util.PasswordField
import com.sushkpavel.leetcode.presentation.util.UsernameField
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = koinViewModel(),
    navHostController: NavHostController = rememberNavController(),
    onRegistrationSuccess: () -> Unit = { navHostController.navigate(Routes.ScreenLogin) },
    onNavigateToLogin: () -> Unit = { navHostController.navigate(Routes.ScreenLogin) }
) {
    val screenState by viewModel.screenState

    RegistrationContent(
        screenState = screenState,
        onEmailChanged = viewModel::onEmailChanged,
        onUsernameChanged = viewModel::onUsernameCahnged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onRegisterClick = { viewModel.onRegisterClicked(onRegistrationSuccess) },
        onNavigateToLogin = onNavigateToLogin
    )
}

@Composable
private fun RegistrationContent(
    screenState: RegistrationScreenState,
    onEmailChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.widthIn(max = 400.dp).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Kursachelo",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                modifier = Modifier.padding(16.dp)
            )

            RegistrationForm(
                screenState = screenState,
                onEmailChanged = onEmailChanged,
                onUsernameChanged = onUsernameChanged,
                onPasswordChanged = onPasswordChanged,
                onRegisterClick = onRegisterClick,
                onNavigateToLogin = onNavigateToLogin
            )
        }
    }
}

@Composable
private fun RegistrationForm(
    screenState: RegistrationScreenState,
    onEmailChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EmailField(
            value = screenState.email,
            onValueChange = onEmailChanged,
            isError = screenState.errorMessage.isNotEmpty()
        )

        UsernameField(
            value = screenState.username,
            onValueChange = onUsernameChanged,
            isError = screenState.errorMessage.isNotEmpty()
        )

        PasswordField(
            value = screenState.password,
            onValueChange = onPasswordChanged
        )

        RegisterButton(onClick = onRegisterClick)

        CustomClickableText("Already hae account?",onNavigateToLogin, modifier = modifier)

        ErrorMessage(errorMessage = screenState.errorMessage)
    }
}

@Composable
private fun RegisterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = MaterialTheme.shapes.small,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        )
    ) {
        Text("Continue", style = MaterialTheme.typography.button)
    }
}


@Preview
@Composable
fun RegistrationScreenPreview() {
    MaterialTheme {
        RegistrationContent(
            screenState = RegistrationScreenState(
                email = "test@example.com",
                username = "testuser",
                password = "",
                errorMessage = ""
            ),
            onEmailChanged = {},
            onUsernameChanged = {},
            onPasswordChanged = {},
            onRegisterClick = {},
            onNavigateToLogin = {}
        )
    }
}

@Preview
@Composable
fun RegistrationScreenErrorPreview() {
    MaterialTheme {
        RegistrationContent(
            screenState = RegistrationScreenState(
                email = "invalid-email",
                username = "",
                password = "123",
                errorMessage = "Invalid email format"
            ),
            onEmailChanged = {},
            onUsernameChanged = {},
            onPasswordChanged = {},
            onRegisterClick = {},
            onNavigateToLogin = {}
        )
    }
}