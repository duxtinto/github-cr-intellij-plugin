package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts.PullRequestRepository;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;

import java.io.IOException;
import java.util.List;

public class GetAllOpenForRepoInteractor implements Interactor<GithubRepositoryEntity, List<GithubPullRequestExt>> {
    private final PullRequestRepository pullRequestRepository;

    public GetAllOpenForRepoInteractor(PullRequestRepository pullRequestRepository) {
        this.pullRequestRepository = pullRequestRepository;
    }

    @Override
    public List<GithubPullRequestExt> run(GithubRepositoryEntity repo) {
        try {
            return pullRequestRepository.getAllOpenBy(repo.ownerName(), repo.name());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
