package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body.UpdateUserProfileBody
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity

object UpdateUserProfileEntityMapper: BaseMapper.TwoWayMapper<UpdateUserProfileEntity, UpdateUserProfileBody> {
    override fun forwardMap(type: UpdateUserProfileEntity): UpdateUserProfileBody = UpdateUserProfileBody(
            type.name,
            type.email,
            type.blog,
            type.company,
            type.location,
            type.hireable,
            type.bio
    )

    override fun reverseMap(type: UpdateUserProfileBody): UpdateUserProfileEntity = UpdateUserProfileEntity(
        type.name,
        type.email,
        type.blog,
        type.company,
        type.location,
        type.hireable,
        type.bio
    )
}