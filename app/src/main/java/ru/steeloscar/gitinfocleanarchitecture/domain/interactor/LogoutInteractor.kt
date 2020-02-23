package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.MainRepository

class LogoutInteractor(private val repository: MainRepository): BaseInteractor() {
    fun execute() {
        repository.clearData()
    }
}