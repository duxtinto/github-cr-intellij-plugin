package io.github.benas.randombeans

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.*
import io.github.benas.randombeans.randomizers.misc.EnumRandomizer

fun EnhancedRandomBuilder.registerRandomizersForPluginClasses(): EnhancedRandomBuilder {
    return this
            .randomize(GithubRepositoryEntity::class.java, RepositoryRandomizer())
            .randomize(IssueEntity::class.java, IssueRandomizer())
            .randomize(PullRequestEntity::class.java, PullRequestRandomizer())
            .randomize(PullRequestEntity.State::class.java, EnumRandomizer(PullRequestEntity.State::class.java))
            .randomize(PullRequestDescriptionEntity::class.java, PullRequestDescriptionRandomizer())
            .randomize(CodeReviewEntity::class.java, CodeReviewRandomizer())
            .randomize(CodeReviewerEntity::class.java, CodeReviewerRandomizer())
}

fun EnhancedRandomBuilder.registerRandomizersForJavaClasses(): EnhancedRandomBuilder {
    return this
}

fun EnhancedRandomBuilder.registerRandomizersForIdeaClasses(): EnhancedRandomBuilder {
    return this
}