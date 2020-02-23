package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.StarsPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class GetStarsInteractor(private val presenter: StarsPresenter, private val repository: Repository): BaseInteractor() {
    fun execute() {
        disposables.add(repository.getStars()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ arrayListStars ->
                if (arrayListStars.isNotEmpty()) {
                    presenter.showResult(arrayListStars)
                } else {
                    presenter.showEmpty()
                }
            },{
                presenter.showError()
            })
        )
    }
}