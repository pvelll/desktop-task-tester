package com.sushkpavel.leetcode.presentation.util

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun CustomClickableText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body1,
    color: Color = MaterialTheme.colors.primary,
    underline: Boolean = false
) {

    Text(
        text = text,
        style = style.copy(
            color = color,
            textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
        ),
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            }
    )
}

@Preview
@Composable
fun CCTPreview(){
    CustomClickableText(
        "hello",
        onClick = {}
    )
}