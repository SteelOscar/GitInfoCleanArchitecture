package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity

interface FollowPresenter {
    fun showError()
    fun showEmpty()
    fun showResult(followProfiles: ArrayList<UserProfileEntity>)
}