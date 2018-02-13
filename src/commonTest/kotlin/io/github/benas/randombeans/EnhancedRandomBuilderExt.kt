package io.github.benas.randombeans

import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.*
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.domain.CodeRevieweeRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.domain.UserRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net.PullRequestResponseAssigneeRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net.PullRequestResponseLinkRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net.PullRequestResponseRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net.UserResponseRandomizer
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import io.github.benas.randombeans.randomizers.misc.EnumRandomizer

fun EnhancedRandomBuilder.registerRandomizersForDomainClasses(): EnhancedRandomBuilder {
    return this
            .randomize(GithubRepositoryEntity::class.java, RepositoryRandomizer())
            .randomize(IssueEntity::class.java, IssueRandomizer())
            .randomize(PullRequestEntity::class.java, PullRequestRandomizer())
            .randomize(PullRequestEntity.State::class.java, EnumRandomizer(PullRequestEntity.State::class.java))
            .randomize(PullRequestDescriptionEntity::class.java, PullRequestDescriptionRandomizer())
            .randomize(CodeReviewEntity::class.java, CodeReviewRandomizer())
            .randomize(CodeReviewCommentEntity::class.java, CodeReviewCommentRandomizer())
            .randomize(CodeReviewerEntity::class.java, CodeReviewerRandomizer())
            .randomize(CodeRevieweeEntity::class.java, CodeRevieweeRandomizer())
            .randomize(UserEntity::class.java, UserRandomizer())
}

fun EnhancedRandomBuilder.registerRandomizersForNetClasses(): EnhancedRandomBuilder {
    return this
            .randomize(PullRequestResponse::class.java, PullRequestResponseRandomizer())
            .randomize(PullRequestResponse.Link::class.java, PullRequestResponseLinkRandomizer())
            .randomize(PullRequestResponse.Assignee::class.java, PullRequestResponseAssigneeRandomizer())
            .randomize(UserResponse::class.java, UserResponseRandomizer())
}

fun EnhancedRandomBuilder.registerRandomizersForJavaClasses(): EnhancedRandomBuilder {
    return this
}

fun EnhancedRandomBuilder.registerRandomizersForIdeaClasses(): EnhancedRandomBuilder {
    return this
}