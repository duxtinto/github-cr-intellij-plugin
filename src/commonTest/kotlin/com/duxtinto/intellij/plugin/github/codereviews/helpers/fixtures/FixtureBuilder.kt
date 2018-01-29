package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

interface FixtureBuilder<out C> {
    fun build(): C
    fun buildList(num: Int): List<C> {
        return (1..num).map { build() }
    }
}
