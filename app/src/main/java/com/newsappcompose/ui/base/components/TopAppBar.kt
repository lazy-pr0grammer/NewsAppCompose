package com.newsappcompose.ui.base.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackAction: () -> Unit = {}
) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(text = "NewsAppCompose")
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackAction) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
        ),
        scrollBehavior = scrollBehavior,
    )
}