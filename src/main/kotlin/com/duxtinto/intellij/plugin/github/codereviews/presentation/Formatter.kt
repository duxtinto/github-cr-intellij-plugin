package com.duxtinto.intellij.plugin.github.codereviews.presentation

interface Formatter<in S, out D> {
    fun format(source: S): D
}
