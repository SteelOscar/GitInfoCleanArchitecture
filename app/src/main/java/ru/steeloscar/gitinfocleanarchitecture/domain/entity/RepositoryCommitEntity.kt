package ru.steeloscar.gitinfocleanarchitecture.domain.entity

import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit

data class RepositoryCommitEntity(
    val message: String?,
    val date: String?,
    val sha: String?,
    private val committer: RepositoryCommit.Committer?,
    private val commit: RepositoryCommit.Commit?
) {
    val committerAvatar: String?
    get() {
        if (committer != null) return committer.avatar_url
        return null
    }

    val committerName: String?
    get() {
        if (committer != null) return committer.login
        return commit?.author?.name
    }
}