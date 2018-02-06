package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.editor

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.editor.IdeaDocumentDriver
import javax.inject.Inject

class IdeaEditorDriver
    @Inject
    constructor(
            private val project: ProjectExt)
    : DomainContract.EditorDriver {
    override fun openDocument(filePath: String): DomainContract.DocumentDriver {
        return IdeaDocumentDriver(
                project,
                project.baseDir.findFileByRelativePath(filePath)!!
        )
    }
}
