package ru.steeloscar.gitinfocleanarchitecture.data.repository

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.GitInfoPreferences
import ru.steeloscar.gitinfocleanarchitecture.data.mapper.*
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.provider.GitHubApiSourceProvider
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.MainRepository
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.*

class MainRepositoryImpl private constructor(private val token: String): MainRepository {

    private val gitHubAPI = GitHubApiSourceProvider.getAPI()

    override fun getUserProfile(): Observable<UserProfileEntity> =
        UserProfileEntityMapper.map( gitHubAPI.getUser(token))

    override fun gerRepositories(): Observable<ArrayList<UserRepositoryEntity>>  =
        ArrayListUserRepositoryEntityMapper.map(gitHubAPI.getRepositories(token))

    override fun getRepositoryCommits(userRepository: String, repository: String): Observable<ArrayList<RepositoryCommitEntity>> =
        ArrayListRepositoryCommitEntityMapper.map(gitHubAPI.getRepositoryCommits(token, userRepository, repository))

    override fun getStars(): Observable<ArrayList<UserRepository>> = gitHubAPI.getStars(token)

    override fun getFollowersUsers(): Observable<ArrayList<UserProfileEntity>> =
        ArrayListUserProfileEntityMapper.map(gitHubAPI.getFollowers(token))

    override fun getOtherUserProfile(login: String): Observable<UserProfileEntity> =
        UserProfileEntityMapper.map(gitHubAPI.getUserInfo(login, token))

    override fun getFollowingUsers(): Observable<ArrayList<UserProfileEntity>>  =
        ArrayListUserProfileEntityMapper.map(gitHubAPI.getFollowingUsers(token))

    override fun updateUserProfile(updateUserProfileEntity: UpdateUserProfileEntity): Observable<UserProfileEntity> =
        UserProfileEntityMapper.map( gitHubAPI.updateProfile(UpdateUserProfileEntityMapper.forwardMap(updateUserProfileEntity), token))

    override fun clearData() {
        OverviewPresenterImpl.clearData()
        RepositoriesPresenterImpl.clearData()
        StarsPresenterImpl.clearData()
        FollowersPresenterImpl.clearData()
        FollowingPresenterImpl.clearData()
        GitInfoPreferences.clearData()
    }

    companion object {
        private var instance: MainRepositoryImpl? = null

        fun newInstance(token: String): MainRepositoryImpl {
            if (instance == null) instance = MainRepositoryImpl(token)
            return instance!!
        }

        fun getInstance(): MainRepositoryImpl = instance!!
    }
}