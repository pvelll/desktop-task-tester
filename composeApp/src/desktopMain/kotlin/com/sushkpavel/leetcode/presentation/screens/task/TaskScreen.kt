package com.sushkpavel.leetcode.presentation.screens.task

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wakaztahir.codeeditor.model.CodeLang
import com.wakaztahir.codeeditor.prettify.PrettifyParser
import com.wakaztahir.codeeditor.theme.CodeTheme
import com.wakaztahir.codeeditor.theme.CodeThemeType
import com.wakaztahir.codeeditor.utils.parseCodeAsAnnotatedString
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    val parser = remember { PrettifyParser() }
    val themeState = remember { mutableStateOf(CodeThemeType.Default) }
    val theme = remember(themeState.value) { themeState.value.theme }
    val screenState by viewModel.screenState.collectAsState()

    TaskScreenContent(
        code = screenState.code,
        language = screenState.language,
        parser = parser,
        theme = theme,
        onCodeChanged = viewModel::onCodeChanged,
        onLanguageChanged = viewModel::onLanguageChanged
    )
}

@Composable
fun TaskScreenContent(
    code: String,
    language: CodeLang,
    parser: PrettifyParser,
    theme: CodeTheme,
    onCodeChanged: (String) -> Unit,
    onLanguageChanged: (CodeLang) -> Unit,
    modifier: Modifier = Modifier
) {
    fun parse(code: String): AnnotatedString {
        return parseCodeAsAnnotatedString(
            parser = parser,
            theme = theme,
            lang = language,
            code = code
        )
    }
    var currentLanguage by remember { mutableStateOf(language) }
    var textValue by remember { mutableStateOf(code) }
    var expanded by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue(parse(code))) }
    var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
    val density = LocalDensity.current

    LaunchedEffect(code, language) {
        if (code != textValue || language != currentLanguage) {
            textValue = code
            currentLanguage = language
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentSize(Alignment.TopStart)
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(currentLanguage.name)
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                CodeLang.entries.forEach { lang ->
                    DropdownMenuItem(
                        onClick = {
                            currentLanguage = lang
                            onLanguageChanged(lang)
                            expanded = false
                        }
                    ) {
                        Text(lang.name)
                    }
                }
            }
        }

        Row {
            if (lineTops.isNotEmpty()) {
                Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                    lineTops.forEachIndexed { index, top ->
                        Text(
                            modifier = Modifier.offset(y = with(density) { top.toDp() }),
                            text = index.toString(),
                            color = MaterialTheme.colors.onBackground.copy(.3f)
                        )
                    }
                }
            }
            BasicTextField(
                modifier = Modifier.fillMaxSize(),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it.copy(annotatedString = parse(it.text))
                    onCodeChanged(textFieldValue.text)
                },
                onTextLayout = { result ->
                    lineTops = Array(result.lineCount) { result.getLineTop(it) }
                }
            )
        }
    }
}

@Preview
@Composable
fun TaskScreenContentPreview() {
    val parser = remember { PrettifyParser() }
    val theme = remember { CodeThemeType.Default.theme }

    TaskScreenContent(
        code = """
            fun main() {
                println("Hello World")
            }
        """.trimIndent(),
        language = CodeLang.Kotlin,
        parser = parser,
        theme = theme,
        onCodeChanged = {},
        onLanguageChanged = {},
        modifier = Modifier
    )
}
