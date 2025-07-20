package com.ydmlabo.markdowneditor.presentation.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
// import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ydmlabo.markdowneditor.presentation.ui.common.ArticleListScreen
import com.ydmlabo.markdowneditor.presentation.ui.main.MarkdownEditorScreen
import com.ydmlabo.markdowneditor.presentation.ui.preview.MarkdownPreviewScreen
import com.ydmlabo.markdowneditor.presentation.viewmodel.MarkdownEditorViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: MarkdownEditorViewModel = viewModel() // Use basic viewModel() instead of hiltViewModel()
) {
    var currentScreen by remember { mutableStateOf(AppScreen.ArticleList) }
    val articles by viewModel.articles.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    
    when (currentScreen) {
        AppScreen.ArticleList -> {
            ArticleListScreen(
                articles = articles,
                onArticleClick = { article ->
                    viewModel.editArticle(article)
                    currentScreen = AppScreen.Editor
                },
                onCreateNew = {
                    viewModel.resetForm()
                    currentScreen = AppScreen.Editor
                },
                modifier = modifier
            )
        }
        
        AppScreen.Editor -> {
            MarkdownEditorScreen(
                onNavigateBack = {
                    currentScreen = AppScreen.ArticleList
                },
                onShowPreview = {
                    currentScreen = AppScreen.Preview
                },
                viewModel = viewModel,
                modifier = modifier
            )
        }
        
        AppScreen.Preview -> {
            MarkdownPreviewScreen(
                markdown = uiState.content,
                onNavigateBack = {
                    currentScreen = AppScreen.Editor
                },
                modifier = modifier
            )
        }
    }
}

enum class AppScreen {
    ArticleList,
    Editor,
    Preview
}
