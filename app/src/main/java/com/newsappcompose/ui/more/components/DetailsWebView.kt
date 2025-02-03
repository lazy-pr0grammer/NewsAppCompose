package com.newsappcompose.ui.more.components

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailsWebView(url: String? = null, onStart: () -> Unit = {}, onFinish: () -> Unit = {}) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )

                settings.javaScriptEnabled = true
                url?.let { data ->
                    loadUrl(data)
                }

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        onFinish()
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        onStart()
                    }
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}