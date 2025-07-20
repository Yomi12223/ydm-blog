package com.ydmlabo.markdowneditor.utils

import org.commonmark.node.Document
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension

object MarkdownProcessor {
    private val parser = Parser.builder()
        .extensions(listOf(TablesExtension.create(), StrikethroughExtension.create()))
        .build()
    
    private val renderer = HtmlRenderer.builder()
        .extensions(listOf(TablesExtension.create(), StrikethroughExtension.create()))
        .build()
    
    fun parseMarkdown(markdown: String): Document {
        return parser.parse(markdown)
    }
    
    fun renderToHtml(markdown: String): String {
        val document = parseMarkdown(markdown)
        return renderer.render(document)
    }
    
    fun renderToHtml(document: Document): String {
        return renderer.render(document)
    }
}
