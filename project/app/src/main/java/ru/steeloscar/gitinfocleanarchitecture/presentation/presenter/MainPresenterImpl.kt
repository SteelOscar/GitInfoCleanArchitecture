package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.RepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.LogoutInteractor
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView

class MainPresenterImpl: BasePresenter<ActivityView.Main>() {

    private val interactor = LogoutInteractor(RepositoryImpl.getInstance())

    fun signOut() {
        interactor.execute()
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }
}