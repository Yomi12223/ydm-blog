package com.ydmlabo.markdowneditor.presentation.ui.preview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.ydmlabo.markdowneditor.utils.MarkdownProcessor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkdownPreviewScreen(
    markdown: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val html = remember(markdown) { 
        MarkdownProcessor.renderToHtml(markdown) 
    }
    
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // トップバー
        TopAppBar(
            title = { 
                Text(
                    text = "プレビュー",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                        contentDescription = "戻る"
                    )
                }
            }
        )
        
        // WebView でHTMLを表示
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { webView ->
            val styledHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                            line-height: 1.6;
                            color: #333;
                            max-width: 800px;
                            margin: 0 auto;
                            padding: 20px;
                        }
                        h1, h2, h3, h4, h5, h6 {
                            color: #2c3e50;
                            margin-top: 2em;
                            margin-bottom: 1em;
                        }
                        h1 { font-size: 2.5em; }
                        h2 { font-size: 2em; }
                        h3 { font-size: 1.5em; }
                        code {
                            background-color: #f4f4f4;
                            padding: 2px 4px;
                            border-radius: 3px;
                            font-family: 'Courier New', monospace;
                        }
                        pre {
                            background-color: #f8f8f8;
                            padding: 16px;
                            border-radius: 6px;
                            overflow-x: auto;
                        }
                        blockquote {
                            border-left: 4px solid #ddd;
                            padding-left: 16px;
                            margin-left: 0;
                            color: #666;
                        }
                        table {
                            border-collapse: collapse;
                            width: 100%;
                            margin: 1em 0;
                        }
                        th, td {
                            border: 1px solid #ddd;
                            padding: 8px;
                            text-align: left;
                        }
                        th {
                            background-color: #f2f2f2;
                        }
                        ul, ol {
                            padding-left: 2em;
                        }
                        li {
                            margin: 0.5em 0;
                        }
                        a {
                            color: #3498db;
                            text-decoration: none;
                        }
                        a:hover {
                            text-decoration: underline;
                        }
                        .highlight {
                            background-color: #fff3cd;
                            padding: 0.2em 0.4em;
                            border-radius: 3px;
                        }
                    </style>
                </head>
                <body>
                    $html
                </body>
                </html>
            """.trimIndent()
            
            webView.loadDataWithBaseURL(null, styledHtml, "text/html", "UTF-8", null)
        }
    }
}
