package ru.steeloscar.gitinfocleanarchitecture.presentation.model

data class UserProfileUI(
    val avatarUrl: String?,
    val name: String?,
    val login: String?,
    val email: String?,
    val blog: String?,
    val company: String?,
    val location: String?,
    val updateDate: String?,
    val joinedDate: String?,
    val bio: String?,
    val followers: Int?,
    val following: Int?,
    val pubGists: Int?,
    val privateGists: Int?,
    val pubRepos: Int?,
    val ownPrivateRepos: Int?,
    val totalPrivateRepos: Int?,
    val diskUsage: Int?,
    val twoFactorAuth: Boolean?,
    val hireable: Boolean?
) {
    companion object {
        var userProfileUI: UserProfileUI? = null
        fun getUpdateUserProfile() =
            UpdateUserProfileUI(
                userProfileUI?.name,
                userProfileUI?.email,
                userProfileUI?.blog,
                userProfileUI?.company,
                userProfileUI?.location,
                userProfileUI?.hireable,
                userProfileUI?.bio
            )
    }
}