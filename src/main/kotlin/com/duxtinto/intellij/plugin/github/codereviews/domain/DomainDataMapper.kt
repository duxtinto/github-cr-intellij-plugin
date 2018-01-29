package com.duxtinto.intellij.plugin.github.codereviews.domain

/**
 * Contract for Mappers that transform non-domain data models into domain entities
 */
interface DomainDataMapper<out E, in M> {
    fun toEntity(dataModel: M): E
}
