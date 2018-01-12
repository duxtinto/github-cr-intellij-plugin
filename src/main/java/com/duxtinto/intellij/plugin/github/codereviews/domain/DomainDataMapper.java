package com.duxtinto.intellij.plugin.github.codereviews.domain;

import java.util.List;

/**
 * Contract for Mappers that transform non-domain data models into domain entities
 */
public interface DomainDataMapper<E, M> {
    E toEntity(M pullRequests);
    List<E> toEntityList(List<M> pullRequests);
}
