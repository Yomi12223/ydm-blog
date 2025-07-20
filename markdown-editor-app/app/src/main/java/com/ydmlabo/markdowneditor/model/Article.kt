package com.ydmlabo.markdowneditor.model

data class Article(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
