package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts.PullRequestRepository;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.GithubRepositoryEntity;

import java.io.IOException;
import java.util.List;

public class GetAllOpenForRepoInteractor implements Interactor<GithubRepositoryEntity, List<PullRequestEntity>> {
    private final PullRequestRepository pullRequestRepository;

    public GetAllOpenForRepoInteractor(PullRequestRepository pullRequestRepository) {
        this.pullRequestRepository = pullRequestRepository;
    }

    @Override
    public List<PullRequestEntity> run(GithubRepositoryEntity repo) {
        try {
            return pullRequestRepository.getAllOpenBy(repo.ownerName(), repo.name());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
