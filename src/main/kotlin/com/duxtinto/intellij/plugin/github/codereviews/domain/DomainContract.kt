package com.duxtinto.intellij.plugin.github.codereviews.domain

interface DomainContract {
    interface EditorDriver {
        fun openFile(filePath: String): Boolean
        fun goToLine(number: Int): Boolean
    }
}