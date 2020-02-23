package ru.steeloscar.gitinfocleanarchitecture.data.mapper

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.BaseMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity

object ArrayListUserRepositoryEntityMapper: BaseMapper.OneWayMapper<ArrayList<UserRepository>, ArrayList<UserRepositoryEntity>> {
    override fun map(type: ArrayList<UserRepository>): ArrayList<UserRepositoryEntity> {
        return type.map {
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