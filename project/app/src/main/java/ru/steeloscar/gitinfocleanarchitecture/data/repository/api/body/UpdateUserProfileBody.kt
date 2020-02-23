package ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body

data class UpdateUserProfileBody(
    val name: String?,
    val email: String?,
    val blog: String?,
    val company: String?,
    val location: String?,
    val hireable: Boolean?,
    val bio: String?
)