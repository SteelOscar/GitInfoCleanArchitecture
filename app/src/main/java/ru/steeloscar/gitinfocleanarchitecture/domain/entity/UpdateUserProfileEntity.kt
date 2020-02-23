package ru.steeloscar.gitinfocleanarchitecture.domain.entity

data class UpdateUserProfileEntity(
    val name: String?,
    val email: String?,
    val blog: String?,
    val company: String?,
    val location: String?,
    val hireable: Boolean?,
    val bio: String?
) {
}