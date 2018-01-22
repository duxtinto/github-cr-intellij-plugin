package com.duxtinto.intellij.plugin.github.codereviews.domain

/**
 * Contract for Mappers that transform non-domain data models into domain entities
 */
interface DomainDataMapper<E, M> {
    fun toEntity(dataModel: M): E
    fun toEntityList(dataModels: List<M>): List<E> {
        return dataModels.map { toEntity(it) }
    }
}
