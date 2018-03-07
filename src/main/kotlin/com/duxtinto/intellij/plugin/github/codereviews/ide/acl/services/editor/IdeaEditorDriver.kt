package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.editor

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.editor.IdeaDocumentDriver
import org.jetbrains.plugins.github.util.GithubUtil
import javax.inject.Inject

class IdeaEditorDriver
    @Inject
    constructor(
            private val project: ProjectExt)
    : DomainContract.EditorDriver {
    override fun openDocument(filePath: String): DomainContract.DocumentDriver {
        val rootFolder = GithubUtil.getGitRepository(project, null)?.root
            ?: throw error("Could not determine root folder of the project's git repository.")
        val file = rootFolder.findFileByRelativePath(filePath)
            ?: throw IllegalStateException("Could not find file at path $filePath (for git root: $rootFolder)")

        return IdeaDocumentDriver(
                project,
                file
        )
    }
}
