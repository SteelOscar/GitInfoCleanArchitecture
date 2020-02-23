package ru.steeloscar.gitinfocleanarchitecture.data.repository

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body.AccessTokenBody
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.provider.GitHubApiSourceProvider
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.AccessTokenEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.StartRepository

class StartRepositoryImpl: StartRepository {

    private val gitHubAPI = GitHubApiSourceProvider.getAPI()

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
}