package ru.steeloscar.gitinfocleanarchitecture.domain.repository

import io.reactivex.Observable
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.AccessTokenEntity

interface StartRepository {
    fun getToken(authenticateCode: String): Observable<AccessTokenEntity>
}