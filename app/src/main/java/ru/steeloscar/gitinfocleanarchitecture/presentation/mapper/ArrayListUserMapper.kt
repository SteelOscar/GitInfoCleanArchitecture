package ru.steeloscar.gitinfocleanarchitecture.presentation.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI

object ArrayListUserMapper: BaseMapper.OneWayMapper<ArrayList<UserProfileEntity>, ArrayList<UserProfileUI>> {
    override fun map(type: ArrayList<UserProfileEntity>): ArrayList<UserProfileUI> {
        return type.map {
            UserProfileUIMapper.map(it)
        } as ArrayList<UserProfileUI>
    }
}