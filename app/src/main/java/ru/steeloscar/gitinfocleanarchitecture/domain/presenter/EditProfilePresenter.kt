package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity

interface EditProfilePresenter {
    fun showError()
    fun showResult(userProfileEntity: UserProfileEntity)
}