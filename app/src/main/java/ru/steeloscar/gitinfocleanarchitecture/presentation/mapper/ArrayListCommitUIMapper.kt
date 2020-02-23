package ru.steeloscar.gitinfocleanarchitecture.presentation.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitUI

object ArrayListCommitUIMapper: BaseMapper.OneWayMapper<ArrayList<RepositoryCommitEntity>, ArrayList<RepositoryCommitUI>> {
    override fun map(type: ArrayList<RepositoryCommitEntity>): ArrayList<RepositoryCommitUI> {
        return type.map {
            RepositoryCommitUI(
                it.message,
                it.date,
                it.sha,
                it.committerAvatar,
                it.committerName
            )
        } as ArrayList<RepositoryCommitUI>
    }
}