package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity

object ArrayListRepositoryCommitEntityMapper: BaseMapper.OneWayMapper<Observable<ArrayList<RepositoryCommit>>, Observable<ArrayList<RepositoryCommitEntity>>> {
    override fun map(type: Observable<ArrayList<RepositoryCommit>>): Observable<ArrayList<RepositoryCommitEntity>> {
        return type.map {list ->
            list.map {
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
}