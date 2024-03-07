package com.novelitech.kotlinchallengeone.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    textTopBar: String,
    body: @Composable (PaddingValues,SnackbarHostState) -> Unit
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = Color(0xFFebdbda),
                        shape = RoundedCornerShape(16.dp),
                        contentColor = Color.Black,
                        dismissActionContentColor = Color.Black
                    )
                }
            )
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3477eb))
                    .padding(16.dp)
            ) {
                Text(
                    text = textTopBar
                )
            }
        }
    ) { innerPadding -> body(innerPadding, snackbarHostState) }
}