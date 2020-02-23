package ru.steeloscar.gitinfocleanarchitecture.domain.entity

class UserRepositoryEntity(
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
    val descriptionRepo: String?,
    val ownerLogin: String,
    val name: String
) {
}