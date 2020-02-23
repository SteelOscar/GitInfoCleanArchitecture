package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class LogoutInteractor(private val repository: Repository): BaseInteractor() {
    fun execute() {
        repository.clearData()
    }
}