package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

interface StartPresenter {
    fun setToken(token: String)
    fun interactorCallback(message: String?)
}