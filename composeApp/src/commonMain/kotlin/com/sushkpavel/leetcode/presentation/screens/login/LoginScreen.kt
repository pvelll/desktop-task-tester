package com.sushkpavel.leetcode.presentation.screens.login

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sushkpavel.leetcode.presentation.navigation.routes.Routes
import com.sushkpavel.leetcode.presentation.util.CustomClickableText
import org.koin.compose.viewmodel.koinViewModel
import com.sushkpavel.leetcode.presentation.util.CustomTextInputField
import com.sushkpavel.leetcode.presentation.util.EmailField
import com.sushkpavel.leetcode.presentation.util.ErrorMessage
import com.sushkpavel.leetcode.presentation.util.PasswordField
import desktopleetcode.composeapp.generated.resources.Res
import desktopleetcode.composeapp.generated.resources.app_name
import desktopleetcode.composeapp.generated.resources.project_name
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navHostController: NavHostController = rememberNavController(),
    onLoginSuccess: () -> Unit = { navHostController.navigate(Routes.TaskScreen) },
    onNavigateToRegistration: () -> Unit = { navHostController.navigate(Routes.ScreenRegistration) }
) {
    val screenState by remember { viewModel.screenState }

    LoginContent(
        screenState = screenState,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClick = { viewModel.onLoginClicked(onLoginSuccess) },
        onNavigateToRegistration = onNavigateToRegistration
    )
}

@Composable
private fun LoginContent(
    screenState: LoginScreenState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(Res.string.project_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                modifier = Modifier.padding(16.dp)
            )

            LoginForm(
                screenState = screenState,
                onEmailChanged = onEmailChanged,
                onPasswordChanged = onPasswordChanged,
                onLoginClick = onLoginClick,
                onNavigateToRegistration = onNavigateToRegistration
            )
        }
    }
}

@Composable
private fun LoginForm(
    screenState: LoginScreenState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onNavigateToRegistration: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.widthIn(max = 400.dp).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EmailField(
            value = screenState.email,
            onValueChange = onEmailChanged,
            isError = screenState.errorMessage.isNotEmpty()
        )

        PasswordField(
            value = screenState.password,
            onValueChange = onPasswordChanged
        )

        LoginButton(onClick = onLoginClick)

        CustomClickableText("Don't have account?",onNavigateToRegistration, modifier = modifier)


        ErrorMessage(errorMessage = screenState.errorMessage)
    }
}

@Composable
private fun LoginButton(
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
fun LoginScreenPreview() {
    MaterialTheme {
        LoginContent(
            screenState = LoginScreenState(
                email = "test@example.com",
                password = "password123",
                errorMessage = ""
            ),
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClick = {},
            onNavigateToRegistration = {}
        )
    }
}

@Preview
@Composable
fun LoginScreenErrorPreview() {
    MaterialTheme {
        LoginContent(
            screenState = LoginScreenState(
                email = "invalid-email",
                password = "",
                errorMessage = "Invalid credentials"
            ),
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClick = {},
            onNavigateToRegistration = {}
        )
    }
}
