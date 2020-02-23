package ru.steeloscar.gitinfocleanarchitecture.domain.repository

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity

interface MainRepository {
    fun getUserProfile(): Observable<UserProfileEntity>
    fun gerRepositories(): Observable<ArrayList<UserRepositoryEntity>>
    fun getRepositoryCommits(userRepository: String, repository: String): Observable<ArrayList<RepositoryCommitEntity>>
    fun getStars(): Observable<ArrayList<UserRepository>>
    fun getFollowersUsers(): Observable<ArrayList<UserProfileEntity>>
    fun getOtherUserProfile(login: String): Observable<UserProfileEntity>
    fun getFollowingUsers(): Observable<ArrayList<UserProfileEntity>>
    fun updateUserProfile(updateUserProfileEntity: UpdateUserProfileEntity): Observable<UserProfileEntity>
    fun clearData()
}