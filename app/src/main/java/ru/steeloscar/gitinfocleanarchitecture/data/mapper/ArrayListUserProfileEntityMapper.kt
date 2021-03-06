package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserProfile
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity

object ArrayListUserProfileEntityMapper: BaseMapper.OneWayMapper<ArrayList<UserProfile>, ArrayList<UserProfileEntity>> {
    override fun map(type: ArrayList<UserProfile>): ArrayList<UserProfileEntity> {
        return type.map { userProfile ->
            UserProfileEntity(
                userProfile.avatar_url,
                userProfile.name,
                userProfile.login,
                userProfile.email,
                userProfile.blog,
                userProfile.company,
                userProfile.location,
                userProfile.updated_at,
                userProfile.created_at,
                userProfile.bio,
                userProfile.followers,
                userProfile.following,
                userProfile.public_gists,
                userProfile.private_gists,
                userProfile.public_repos,
                userProfile.owned_private_repos,
                userProfile.total_private_repos,
                userProfile.disk_usage,
                userProfile.two_factor_authentication,
                userProfile.hireable)
        } as ArrayList<UserProfileEntity>
    }
}