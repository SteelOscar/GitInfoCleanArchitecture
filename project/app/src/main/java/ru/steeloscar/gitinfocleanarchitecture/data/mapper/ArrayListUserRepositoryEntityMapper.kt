package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity

object ArrayListUserRepositoryEntityMapper: BaseMapper.OneWayMapper<Observable<ArrayList<UserRepository>>, Observable<ArrayList<UserRepositoryEntity>>> {
    override fun map(type: Observable<ArrayList<UserRepository>>): Observable<ArrayList<UserRepositoryEntity>> {
        return type.map { list ->
            list.map {
                UserRepositoryEntity(
                    it.full_name,
                    it.fork,
                    it.archived,
                    it.private,
                    it.stargazers_count,
                    it.watchers_count,
                    it.language,
                    it.created_at,
                    it.updated_at,
                    it.size,
                    it.description,
                    it.owner.login.toString(),
                    it.name.toString()
                )
            } as ArrayList<UserRepositoryEntity>
        }
    }
}