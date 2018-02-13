package com.duxtinto.intellij.plugin.github.codereviews.data

import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity

interface DataContract {
    interface User {
        interface Fetcher {
            fun fetchAuthenticated(): UserEntity?
        }
    }
}
