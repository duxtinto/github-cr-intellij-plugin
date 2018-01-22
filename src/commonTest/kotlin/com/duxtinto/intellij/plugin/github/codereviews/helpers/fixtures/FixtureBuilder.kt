package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

interface FixtureBuilder<out C> {
    fun build(): C
}
