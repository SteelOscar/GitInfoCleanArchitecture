package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.OverviewPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class GetUserProfileInteractor(private val presenter: OverviewPresenter, private val repository: Repository): BaseInteractor() {
    fun execute() {
        disposables.add(
            repository.getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ profile ->
                    presenter.showResult(profile)
                }, {
                    presenter.showError()
                })
        )
    }
}