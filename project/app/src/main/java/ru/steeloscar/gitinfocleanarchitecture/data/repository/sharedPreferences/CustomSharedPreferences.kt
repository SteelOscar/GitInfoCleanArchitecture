package ru.steeloscar.gitinfocleanarchitecture.data.repository.sharedPreferences

import android.content.SharedPreferences
import io.reactivex.Completable
import ru.steeloscar.gitinfocleanarchitecture.commons.GitInfoPreferences

class CustomSharedPreferences(private val sharedPreferences: SharedPreferences) {

    private val tokenKey = "token"

    fun setToken(token: String) {
        sharedPreferences.edit().putString(tokenKey, token).apply()
        if (token.isBlank()) GitInfoPreferences.setToken(null)
        else GitInfoPreferences.setToken(token)

    }
}