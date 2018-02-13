package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse

data class CodeReviewResponse(
        val id: Long,
        val user: UserResponse,
        val state: String,
        val _links: Links = Links(emptyMap())) {

    data class Links(val pull_request: Map<String, String>)
}
