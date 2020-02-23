package ru.steeloscar.gitinfocleanarchitecture.commons

import android.content.SharedPreferences
import ru.steeloscar.gitinfocleanarchitecture.data.repository.sharedPreferences.CustomSharedPreferences

class GitInfoPreferences(preferences: SharedPreferences) {

    private val customPreferences = CustomSharedPreferences(preferences)

    init {
        with(preferences.getString("token", null)) {
            if (!this.isNullOrBlank()) token = this
        }
    }

    fun setTokenSharedPreferences(_token: String) {
        token = if (_token.isBlank()) null
        else _token
        customPreferences.setToken(_token)
    }

    companion object {
        private var login: String? = null
        private var token: String? = null

        fun getLogin() = login

        fun getToken(): String? {
           return token
        }

        fun getTokenAPI(): String = "token $token"

        fun setLogin(login: String?) {
            this.login = login
        }

        fun setToken(token: String?) {
            this.token = token
        }

        fun clearData() {
            login = null
            token = null
        }
    }
}