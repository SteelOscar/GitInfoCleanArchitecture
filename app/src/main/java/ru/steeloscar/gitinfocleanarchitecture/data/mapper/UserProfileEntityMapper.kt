package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserProfile
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity


object UserProfileEntityMapper: BaseMapper.OneWayMapper<UserProfile, UserProfileEntity>{
    override fun map(type: UserProfile): UserProfileEntity {
        return type.let {
            UserProfileEntity(
                it.avatar_url,
                it.name,
                it.login,
                it.email,
                it.blog,
                it.company,
                it.location,
                it.updated_at,
                it.created_at,
                it.bio,
                it.followers,
                it.following,
                it.public_gists,
                it.private_gists,
                it.public_repos,
                it.owned_private_repos,
                it.total_private_repos,
                it.disk_usage,
                it.two_factor_authentication,
                it.hireable)
        }
    }
}