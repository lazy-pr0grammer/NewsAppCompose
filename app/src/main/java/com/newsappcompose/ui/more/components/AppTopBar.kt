package com.newsappcompose.ui.more.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, source: String, onBackAction: () -> Unit = {}) {
    TopAppBar(
        title = {
            Column {
                Text(text = title, maxLines = 1, fontSize = 14.sp)
                Text(text = source, maxLines = 1, fontSize = 11.sp)
            }
        },
        navigationIcon = {
            IconButton(onBackAction) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}