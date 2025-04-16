package com.sushkpavel.leetcode.presentation.screens.task

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import com.sushkpavel.desktopleetcode.domain.model.submission.TestResult
import com.sushkpavel.desktopleetcode.domain.model.task.Difficulty
import com.sushkpavel.desktopleetcode.domain.model.task.Task
import com.sushkpavel.leetcode.utils.toPrettyString
import java.time.Instant
import java.time.ZoneOffset

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
        task = screenState.task,
        testResult = screenState.testResult,
        code = screenState.code,
        language = screenState.language,
        parser = parser,
        theme = theme,
        onCodeChanged = viewModel::onCodeChanged,
        onGetTask = viewModel::onGetTask,
        onLanguageChanged = viewModel::onLanguageChanged,
        onSubmit = viewModel::submitTask
    )
}

@Composable
fun TaskScreenContent(
    task: Task?,
    testResult: TestResult?,
    code: String,
    language: CodeLang,
    parser: PrettifyParser,
    theme: CodeTheme,
    onCodeChanged: (String) -> Unit,
    onLanguageChanged: (CodeLang) -> Unit,
    onSubmit: () -> Unit,
    onGetTask: (Difficulty) -> Unit,
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
    var currentDifficulty by remember { mutableStateOf(Difficulty.EASY) }
    var textValue by remember { mutableStateOf(code) }
    var languageExpanded by remember { mutableStateOf(false) }
    var difficultyExpanded by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue(parse(code))) }
    var lineTops by remember { mutableStateOf(emptyArray<Float>()) }
    val density = LocalDensity.current
    val scrollState = rememberScrollState()

    LaunchedEffect(code, language) {
        if (code != textValue || language != currentLanguage) {
            textValue = code
            currentLanguage = language
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                OutlinedButton(
                    onClick = { languageExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(currentLanguage.name)
                    Icon(
                        imageVector = if (languageExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = languageExpanded,
                    onDismissRequest = { languageExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CodeLang.entries.forEach { lang ->
                        DropdownMenuItem(
                            onClick = {
                                currentLanguage = lang
                                onLanguageChanged(lang)
                                languageExpanded = false
                            }
                        ) {
                            Text(lang.name)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                OutlinedButton(
                    onClick = { difficultyExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(currentDifficulty.name)
                    Icon(
                        imageVector = if (difficultyExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = difficultyExpanded,
                    onDismissRequest = { difficultyExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Difficulty.entries.forEach { difficulty ->
                        DropdownMenuItem(
                            onClick = {
                                currentDifficulty = difficulty
                                difficultyExpanded = false
                            }
                        ) {
                            Text(difficulty.name)
                        }
                    }
                }
            }

            Button(
                onClick = { onGetTask(currentDifficulty) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Get Task")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        task?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = task.id.toString() + "." + task.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (task.examples.isNotBlank()) {
                    Text(
                        text = "Examples:",
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = task.examples,
                        style = MaterialTheme.typography.body2
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
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

        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Submit Solution")
        }

        testResult?.let {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(24.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                        color = if(it.success) Color.Green else Color.Red
                    )
            ){
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("Actual Result: ${it.actualResult}")
                    Text("Task id: ${it.taskId}")
                    Text("User id: ${it.userId}")
                    Text("Date: ${it.createdAt.toPrettyString()}")
                }
            }
        }

    }
}




@Preview
@Composable
fun TaskScreenContentPreview() {
    val parser = remember { PrettifyParser() }
    val theme = remember { CodeThemeType.Default.theme }

    TaskScreenContent(
        task = Task(
            id = 1,
            title = "Sample Task",
            description = "Write a function that calculates the sum of two numbers",
            examples = "Input: 2, 3\nOutput: 5",
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            difficulty = Difficulty.MEDIUM
        ),
        code = """
            fun sum(a: Int, b: Int): Int {
                return a + b
            }
        """.trimIndent(),
        language = CodeLang.Kotlin,
        parser = parser,
        theme = theme,
        onCodeChanged = {},
        onLanguageChanged = {},
        onGetTask = {},
        onSubmit = {},
        modifier = Modifier,
        testResult = TestResult(1,1,1,1,"string", false, Instant.now())
    )
}
