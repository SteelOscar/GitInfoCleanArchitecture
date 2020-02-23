package ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseInteractor {

    protected val disposables = CompositeDisposable()

    fun clear() {
        disposables.clear()
    }
}