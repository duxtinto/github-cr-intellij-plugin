package com.duxtinto.intellij.plugin.github.codereviews.domain;

import javax.annotation.Nullable;

public interface Interactor<I, O> {
    @Nullable
    O run(I request);
}
