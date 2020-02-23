package ru.steeloscar.gitinfocleanarchitecture.data.repository.sharedPreferences

import android.content.SharedPreferences

class CustomSharedPreferences(private val sharedPreferences: SharedPreferences) {

    private val tokenKey = "token"

    fun setToken(token: String) {
        sharedPreferences.edit().putString(tokenKey, token).apply()
    }
}