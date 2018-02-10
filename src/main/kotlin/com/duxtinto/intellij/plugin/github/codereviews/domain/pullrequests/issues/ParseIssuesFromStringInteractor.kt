package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity

interface ParseIssuesFromStringInteractor: Interactor<String, List<IssueEntity>>