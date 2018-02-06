package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.editor

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.vfs.VirtualFile

class IdeaDocumentDriver(
        private val project: ProjectExt,
        private val file: VirtualFile)
    : DomainContract.DocumentDriver {

    override fun goToLine(num: Int) {
        OpenFileDescriptor(project, file, num, 0).navigate(true)
    }
}