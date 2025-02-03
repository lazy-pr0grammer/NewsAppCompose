package com.newsappcompose.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.newsappcompose.data.model.article.Article
import com.newsappcompose.data.toDate
import kotlinx.coroutines.Dispatchers

@Composable
fun NewsContent(article: Article, onItemClicked: () -> Unit) {
    Card(
        onClick = onItemClicked,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(Modifier) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.urlToImage)
                        .coroutineContext(Dispatchers.IO)
                        .crossfade(true)
                        .build(),
                    contentDescription = "NewsThumbnail",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                )

                article.author?.let {
                    Card(modifier = Modifier.align(Alignment.TopEnd).padding(horizontal = 5.dp, vertical = 8.dp), shape = MaterialTheme.shapes.extraLarge) {
                        Text(
                            text = it,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(5.dp),
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                article.title?.let {
                    Text(text = it, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(8.dp))
                article.description?.let {
                    Text(text = it, fontSize = 12.sp)
                }
                article.publishedAt?.let {
                    Text(
                        text = it.toDate() ?: "Unknown",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}