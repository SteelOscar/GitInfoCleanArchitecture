package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity

interface OverviewPresenter {
    fun showResult(userProfileEntity: UserProfileEntity)
    fun showError()
}