package com.sushkpavel.leetcode.presentation.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Login",
    placeholder: String = "Enter your username or email",
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    errorMessage: String? = null,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.width(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) {
                keyboardOptions.copy(keyboardType = KeyboardType.Password)
            } else {
                keyboardOptions
            },
            keyboardActions = keyboardActions,
            isError = isError
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
@Preview
@Composable
fun DesktopLeetcodeTextInputFieldPreview(){
    CustomTextInputField(value = "Hello", onValueChange = {})
}