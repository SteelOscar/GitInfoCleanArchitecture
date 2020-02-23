package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity

object ArrayListRepositoryCommitEntityMapper: BaseMapper.OneWayMapper<ArrayList<RepositoryCommit>, ArrayList<RepositoryCommitEntity>> {
    override fun map(type: ArrayList<RepositoryCommit>): ArrayList<RepositoryCommitEntity> {
        return type.map {
            RepositoryCommitEntity(
                it.commit?.message,
                it.commit?.author?.date,
                it.sha,
                it.author,
                it.commit
            )
        } as ArrayList<RepositoryCommitEntity>
    }
}