package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetAllClosableByInteractor implements Interactor<String, List<IssueEntity>> {

    private final PullRequestDomainContract.DescriptionParser descriptionParser;

    public GetAllClosableByInteractor(PullRequestDomainContract.DescriptionParser descriptionParser) {
        this.descriptionParser = descriptionParser;
    }

    @Override
    public List<IssueEntity> run(@Nonnull String description) {
        checkNotNull(description);

        return transformToEntityList(parseForClosableIssues(description));
    }

    private List<IssueEntity> transformToEntityList(Map<Long, URL> closableIssueMap) {
        return closableIssueMap
                .entrySet()
                .stream()
                .map(entry -> IssueEntity.create(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @NotNull
    private Map<Long, URL> parseForClosableIssues(@Nonnull String description) {
        return descriptionParser
                .parse(description)
                .closableIssues();
    }
}
