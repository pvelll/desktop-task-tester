package com.sushkpavel.leetcode.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomTextInputField(
        value = value,
        onValueChange = onValueChange,
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
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    CustomTextInputField(
        value = value,
        onValueChange = onValueChange,
        label = "Username",
        placeholder = "berezkina228",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        },
        isError = isError,
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    CustomTextInputField(
        value = value,
        onValueChange = onValueChange,
        label = "Email",
        placeholder = "your.email@example.com",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
            )
        },
        isError = isError,
        modifier = modifier.fillMaxWidth()
    )
}
