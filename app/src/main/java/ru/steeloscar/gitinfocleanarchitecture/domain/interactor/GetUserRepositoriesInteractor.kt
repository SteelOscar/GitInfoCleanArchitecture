package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.RepositoryPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.MainRepository

class GetUserRepositoriesInteractor(private val presenter: RepositoryPresenter, private val repository: MainRepository): BaseInteractor()  {

    fun execute() {
        disposables.add(repository.gerRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()){
                    presenter.showResult(it)
                    getCommits(it)
                } else {
                    presenter.showEmpty()
                }
            },{
                presenter.showError()
            })
        )
    }

    private fun getCommits(arrayListRepositoriesEntity: ArrayList<UserRepositoryEntity>) {
        arrayListRepositoriesEntity.map { userRepository ->
            disposables.add(repository.getRepositoryCommits(userRepository.ownerLogin, userRepository.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    presenter.showCommits(userRepository.repoName.toString(), it)
                }
            )
        }
    }
}