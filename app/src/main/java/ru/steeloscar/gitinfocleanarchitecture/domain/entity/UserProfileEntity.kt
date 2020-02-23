package ru.steeloscar.gitinfocleanarchitecture.domain.entity

data class UserProfileEntity(
    var avatarUrl: String?,
    var name: String?,
    var login: String?,
    var email: String?,
    var blog: String?,
    var company: String?,
    var location: String?,
    var updateDate: String?,
    var joinedDate: String?,
    var bio: String?,
    var followers: Int?,
    var following: Int?,
    var pubGists: Int?,
    var privateGists: Int?,
    var pubRepos: Int?,
    var ownPrivateRepos: Int?,
    var totalPrivateRepos: Int?,
    var diskUsage: Int?,
    var twoFactorAuth: Boolean?,
    var hireable: Boolean?
) {
    fun setProfile(userProfileEntity: UserProfileEntity) {
        userProfileEntity.let {
            avatarUrl = it.avatarUrl
            name = it.name
            login = it.login
            email = it.email
            blog = it.blog
            company = it.company
            location = it.location
            updateDate = it.updateDate
            joinedDate = it.joinedDate
            bio = it.bio
            followers = it.followers
            following = it.following
            pubGists = it.pubGists
            privateGists = it.privateGists
            pubRepos = it.pubRepos
            ownPrivateRepos = it.ownPrivateRepos
            totalPrivateRepos = it.totalPrivateRepos
            diskUsage = it.diskUsage
            twoFactorAuth = it.twoFactorAuth
            hireable = it.hireable
        }
    }
}