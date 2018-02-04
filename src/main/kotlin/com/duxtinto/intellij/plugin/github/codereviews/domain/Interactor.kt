package com.duxtinto.intellij.plugin.github.codereviews.domain

interface Interactor<in I, out O> {
    fun run(request: I): O?
}
