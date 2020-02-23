package ru.steeloscar.gitinfocleanarchitecture.presentation.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserRepositoryUI

object ArrayListRepositoryUIMapper: BaseMapper.OneWayMapper<ArrayList<UserRepositoryEntity>, ArrayList<UserRepositoryUI>> {
    override fun map(type: ArrayList<UserRepositoryEntity>): ArrayList<UserRepositoryUI> {
        return type.map {
            UserRepositoryUI(
                it.repoName,
                it.forkRepo,
                it.archivedRepo,
                it.privateRepo,
                it.starCnt,
                it.watchCnt,
                it.language,
                it.createdDate,
                it.updateDate,
                it.sizeRepo,
                it.descriptionRepo
            )
        }  as ArrayList<UserRepositoryUI>
    }
}