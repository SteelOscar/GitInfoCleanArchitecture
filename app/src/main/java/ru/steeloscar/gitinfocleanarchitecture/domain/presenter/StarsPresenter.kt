package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import java.util.ArrayList

interface StarsPresenter {
    fun showError()
    fun showEmpty()
    fun showResult(arrayListStars: ArrayList<UserRepository>)
}