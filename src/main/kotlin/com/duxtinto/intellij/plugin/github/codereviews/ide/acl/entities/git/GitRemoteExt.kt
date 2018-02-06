package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git

import git4idea.repo.GitRemote

class GitRemoteExt(private val ideaRemote: GitRemote) : Comparable<GitRemote> by ideaRemote
