package ru.steeloscar.gitinfocleanarchitecture.presentation.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI

object UserProfileUIMapper: BaseMapper.OneWayMapper<UserProfileEntity, UserProfileUI> {
    override fun map(type: UserProfileEntity): UserProfileUI {
        return with(type) {
            UserProfileUI(
                avatarUrl,
                name,
                login,
                email,
                blog,
                company,
                location,
                updateDate,
                joinedDate,
                bio,
                followers,
                following,
                pubGists,
                privateGists,
                pubRepos,
                ownPrivateRepos,
                totalPrivateRepos,
                diskUsage,
                twoFactorAuth,
                hireable
            )
        }
    }
}