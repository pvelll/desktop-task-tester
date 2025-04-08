package com.sushkpavel.leetcode.presentation.screens.registration

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sushkpavel.leetcode.presentation.navigation.routes.Routes
import com.sushkpavel.leetcode.presentation.util.CustomClickableText
import com.sushkpavel.leetcode.presentation.util.CustomTextInputField
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    val screenState by viewModel.screenState

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.widthIn(max = 400.dp).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

//            Icon(
//                painter = painterResource("ic_logo.xml"),
//                contentDescription = "App Logo",
//                modifier = Modifier.size(64.dp),
//                tint = MaterialTheme.colors.primary
//            )

            Text(
                text = "Kursachelo",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                modifier = Modifier.padding(16.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CustomTextInputField(
                    value = screenState.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    label = "Email",
                    placeholder = "your.email@example.com",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    isError = screenState.errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

                CustomTextInputField(
                    value = screenState.email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    label = "Username",
                    placeholder = "berezkina228",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    isError = screenState.errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                CustomTextInputField(
                    value = screenState.password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    label = "Password",
                    placeholder = "••••••••",
                    isPassword = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.onLoginClicked(onSuccess = {
//                        navHostController.navigate()
                        })
                    },
                    modifier = Modifier
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
            CustomClickableText(
                text = "Already have an account?",
                onClick = {
                    navHostController.navigate(Routes.ScreenLogin)
                }
            )
            if (screenState.errorMessage.isNotEmpty()) {
                Text(
                    text = screenState.errorMessage,
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            }

        }
    }
}
