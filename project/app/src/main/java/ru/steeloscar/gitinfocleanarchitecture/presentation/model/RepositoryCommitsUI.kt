package ru.steeloscar.gitinfocleanarchitecture.presentation.model

data class RepositoryCommitsUI(
    val repoName: String,
    val commits: ArrayList<RepositoryCommitUI>
)