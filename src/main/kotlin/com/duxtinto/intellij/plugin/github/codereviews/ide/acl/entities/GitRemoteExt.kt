package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import git4idea.repo.GitRemote

class GitRemoteExt(private val remote: GitRemote) : Comparable<GitRemote> by remote
