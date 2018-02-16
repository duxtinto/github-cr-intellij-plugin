package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse

data class RequestedReviewersResponse(
        val users: List<UserResponse> = listOf()
)
