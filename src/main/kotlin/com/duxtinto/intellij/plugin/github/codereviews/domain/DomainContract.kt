package com.duxtinto.intellij.plugin.github.codereviews.domain

interface DomainContract {
    interface EditorDriver {
        fun openDocument(filePath: String): DocumentDriver?
    }

    interface DocumentDriver {
        fun goToLine(num: Int)
    }
}