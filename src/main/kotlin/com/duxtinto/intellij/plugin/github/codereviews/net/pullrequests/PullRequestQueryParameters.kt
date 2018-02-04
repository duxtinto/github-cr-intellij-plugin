package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests

data class PullRequestQueryParameters(
        val state: State = State.OPEN,
        val head: String = "",
        val base: String = "",
        val sort: Sort = Sort.CREATED,
        val direction: Direction = Direction.DESC,
        val pageSize: Int = 100) {

    fun toQueryString(): String {
        return "per_page=" + this.pageSize +
                "&state=" + this.state +
                if (!head.isEmpty()) "&head=" + this.head else "" +
                if (!base.isEmpty()) "&base=" + this.base else "" +
                "&sort=" + sort +
                "&direction=" + direction
    }

    enum class State(private val state: String) {
        OPEN("open"),
        CLOSED("closed"),
        ALL("all");

        override fun toString(): String {
            return state
        }
    }

    enum class Sort(private val sort: String) {
        CREATED("created"),
        UPDATED("updated"),
        POPULARITY("popularity"),
        LONG_RUNNING("long-runing");

        override fun toString(): String {
            return sort
        }
    }

    enum class Direction(private val direction: String) {
        ASC("asc"),
        DESC("desc");

        override fun toString(): String {
            return direction
        }
    }
}
