package com.sushkpavel.leetcode.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.sushkpavel.leetcode.presentation.navigation.AppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navHostController = rememberNavController()
    MaterialTheme {
        AppNavGraph(navHostController, paddingValues = PaddingValues(12.dp))
    }
}