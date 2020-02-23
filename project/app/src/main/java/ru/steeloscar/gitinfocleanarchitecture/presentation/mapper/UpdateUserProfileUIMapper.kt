package ru.steeloscar.gitinfocleanarchitecture.presentation.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UpdateUserProfileUI


object UpdateUserProfileUIMapper: BaseMapper.TwoWayMapper<UpdateUserProfileUI, UpdateUserProfileEntity> {
    override fun forwardMap(type: UpdateUserProfileUI): UpdateUserProfileEntity = UpdateUserProfileEntity(
        type.name,
        type.email,
        type.blog,
        type.company,
        type.location,
        type.hireable,
        type.bio
    )
    override fun reverseMap(type: UpdateUserProfileEntity): UpdateUserProfileUI = UpdateUserProfileUI(
        type.name,
        type.email,
        type.blog,
        type.company,
        type.location,
        type.hireable,
        type.bio
    )
}