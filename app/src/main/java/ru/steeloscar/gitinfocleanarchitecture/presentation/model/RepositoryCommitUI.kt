package ru.steeloscar.gitinfocleanarchitecture.presentation.model

data class RepositoryCommitUI(
    val message: String?,
    val date: String?,
    val sha: String?,
    val committerAvatar: String?,
    val committerName: String?
)