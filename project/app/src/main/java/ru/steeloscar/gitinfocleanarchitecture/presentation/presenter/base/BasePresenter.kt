package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base

abstract class BasePresenter<out T> {

    private var view: T? = null

    fun getView() = view!!

    @Suppress("UNCHECKED_CAST")
    fun attachView(view: Any?) {
        this.view = view as T?
    }

    open fun detachView() {
        view = null
    }
}