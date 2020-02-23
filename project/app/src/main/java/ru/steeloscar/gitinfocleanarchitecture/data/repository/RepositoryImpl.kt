package ru.steeloscar.gitinfocleanarchitecture.data.repository

import android.content.SharedPreferences
import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.commons.GitInfoPreferences
import ru.steeloscar.gitinfocleanarchitecture.data.mapper.*
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.provider.GitHubApiSourceProvider
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body.AccessTokenBody
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.data.repository.sharedPreferences.CustomSharedPreferences
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.*
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.*

class RepositoryImpl(sharedPreferences: SharedPreferences): Repository {

    private val preferences =
        CustomSharedPreferences(sharedPreferences)

    private val gitHubAPI = GitHubApiSourceProvider.getAPI()
    private var token = "token ${GitInfoPreferences.getToken()}"

    private fun updateToken(){
        token = "token ${GitInfoPreferences.getToken()}"
    }

    override fun getToken(authenticateCode: String): Observable<AccessTokenEntity> =
        gitHubAPI.getAccessToken("https://github.com/login/oauth/access_token","application/json",
            AccessTokenBody(
                AppConstants.CLIENT_ID,
                AppConstants.CLIENT_SECRET,
                authenticateCode,
                AppConstants.REDIRECT_URI
            )
        ).map {
            AccessTokenEntity(it.accessToken)
        }

    override fun setTokenSharedPreferences(token: String) {
        preferences.setToken(token)
        updateToken()
    }

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
        setTokenSharedPreferences("")
        OverviewPresenterImpl.clearData()
        RepositoriesPresenterImpl.clearData()
        StarsPresenterImpl.clearData()
        FollowersPresenterImpl.clearData()
        FollowingPresenterImpl.clearData()
        GitInfoPreferences.clearData()
    }

    companion object {
        private var instance: RepositoryImpl? = null

        fun newInstance(sharedPreferences: SharedPreferences): RepositoryImpl {
            if (instance == null) instance = RepositoryImpl(sharedPreferences)
            return instance as RepositoryImpl
        }

        fun getInstance(): RepositoryImpl = instance as RepositoryImpl
    }
}