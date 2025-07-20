package com.ydmlabo.markdowneditor.presentation.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ydmlabo.markdowneditor.presentation.theme.YdmLaboTheme
import com.ydmlabo.markdowneditor.presentation.viewmodel.MarkdownEditorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkdownEditorScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
    onShowPreview: () -> Unit = {},
    viewModel: MarkdownEditorViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    
    // エラーメッセージの表示
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            // エラーハンドリング
        }
    }
    
    // 成功メッセージの表示
    LaunchedEffect(uiState.showSuccessMessage) {
        if (uiState.showSuccessMessage) {
            // 成功メッセージを表示後、3秒後に自動で消去
            kotlinx.coroutines.delay(3000)
            viewModel.clearSuccessMessage()
        }
    }
    
    YdmLaboTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // トップバー
                TopAppBar(
                    title = { 
                        Text(
                            text = "YDM.LABO 記事作成",
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
                    },
                    actions = {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            TextButton(
                                onClick = { 
                                    viewModel.saveArticle()
                                    keyboardController?.hide()
                                }
                            ) {
                                Text(if (uiState.editingArticle != null) "更新" else "保存")
                            }
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // タイトル入力
                OutlinedTextField(
                    value = uiState.title,
                    onValueChange = { viewModel.updateTitle(it) },
                    label = { Text("記事タイトル") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    isError = uiState.error != null && uiState.title.isBlank(),
                    enabled = !uiState.isLoading
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // マークダウン編集エリア
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "マークダウン編集",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        BasicTextField(
                            value = uiState.content,
                            onValueChange = { viewModel.updateContent(it) },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            enabled = !uiState.isLoading,
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    if (uiState.content.isEmpty()) {
                                        Text(
                                            text = "# YDM.LABOへようこそ\n\nここにマークダウンで記事を書いてください...\n\n## 例：\n- リスト項目\n- **太字**\n- *斜体*\n- `コード`",
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = FontFamily.Monospace,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                            )
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // エラーメッセージの表示
                if (uiState.error != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = uiState.error,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // 成功メッセージの表示
                if (uiState.showSuccessMessage) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(
                            text = "記事を保存しました！",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                // 操作ボタン
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onShowPreview,
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.isLoading && uiState.content.isNotBlank()
                    ) {
                        Text("プレビュー")
                    }
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Button(
                        onClick = { 
                            viewModel.saveArticle()
                        },
                        modifier = Modifier.weight(1f),
                        enabled = !uiState.isLoading && uiState.title.isNotBlank()
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(16.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(if (uiState.editingArticle != null) "更新" else "投稿")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewMarkdownEditor() {
    MarkdownEditorScreen()
}
