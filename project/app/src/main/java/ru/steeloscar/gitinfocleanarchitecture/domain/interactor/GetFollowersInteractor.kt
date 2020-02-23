package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.FollowPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class GetFollowersInteractor(private val presenter: FollowPresenter, private val repository: Repository): BaseInteractor() {

    private val getOtherUserInteractor = GetOtherUserProfileInteractor(presenter,repository)

    fun execute() {
        disposables.add(repository.getFollowersUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({    followersProfiles ->
                if (followersProfiles.isNotEmpty()) getOtherUserInteractor.execute(followersProfiles)
                else presenter.showEmpty()
            },{
                presenter.showError()
            })
        )
    }
}