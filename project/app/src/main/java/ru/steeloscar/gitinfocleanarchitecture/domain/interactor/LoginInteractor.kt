package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.StartPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.StartRepository

class LoginInteractor(private val presenter: StartPresenter, private val repository: StartRepository): BaseInteractor() {
    fun execute(authenticateCode: String) {
        disposables.add(repository.getToken(authenticateCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                presenter.setToken(token = response.token)
                presenter.interactorCallback(null)
            }, {
                presenter.interactorCallback("connection")
            })
        )
    }
}