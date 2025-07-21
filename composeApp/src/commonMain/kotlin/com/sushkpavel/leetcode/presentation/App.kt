package com.sushkpavel.leetcode.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.sushkpavel.desktopleetcode.data.dataModule
import com.sushkpavel.leetcode.di.useCaseModule
import com.sushkpavel.leetcode.di.viewModelModule
import com.sushkpavel.leetcode.presentation.navigation.AppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {
    startKoin {
        modules(dataModule, useCaseModule, viewModelModule)
    }
    MaterialTheme {
        AppNavGraph(paddingValues = PaddingValues(12.dp))
    }
}