package ru.steeloscar.gitinfocleanarchitecture.presentation.model

data class UserRepositoryUI(
    val repoName: String?,
    val forkRepo: Boolean?,
    val archivedRepo: Boolean?,
    val privateRepo: Boolean?,
    val starCnt: Int?,
    val watchCnt: Int?,
    val language: String?,
    val createdDate: String?,
    val updateDate: String?,
    val sizeRepo: Int?,
    val descriptionRepo: String?
) {
    var commits = ArrayList<RepositoryCommitUI>()
    var visibilityCommits = false
}