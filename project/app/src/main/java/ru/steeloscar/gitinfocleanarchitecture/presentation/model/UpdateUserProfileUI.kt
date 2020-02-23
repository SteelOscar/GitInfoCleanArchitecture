package ru.steeloscar.gitinfocleanarchitecture.presentation.model

data class UpdateUserProfileUI(
    val name: String?,
    val email: String?,
    val blog: String?,
    val company: String?,
    val location: String?,
    val hireable: Boolean?,
    val bio: String?
)