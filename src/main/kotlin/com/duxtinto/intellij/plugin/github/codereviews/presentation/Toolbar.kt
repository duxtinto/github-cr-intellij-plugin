package com.duxtinto.intellij.plugin.github.codereviews.presentation

interface Toolbar {
    interface Factory {
        fun create(): Toolbar
    }
}
