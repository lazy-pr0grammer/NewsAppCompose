package com.newsappcompose.data

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDate(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val date = sdf.parse(this)

    val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())

    return date?.let {
        outputFormat.format(it)
    }
}