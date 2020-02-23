package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.MainRepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.GetUserRepositoriesInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.RepositoryPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.ArrayListCommitUIMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.ArrayListRepositoryUIMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitsUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.RepositoriesRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.RepositoriesFragment

class RepositoriesPresenterImpl private constructor(): BasePresenter<FragmentView.Repositories>(), RepositoryPresenter {

    private val interactor = GetUserRepositoriesInteractor(this, MainRepositoryImpl.getInstance())

    fun refreshData() {
        getView().showRefresh()
        getRepositories()
    }

    fun loadingData() {
        getView().showProgress()
        getRepositories()
    }

    private fun getRepositories(){
        interactor.execute()
    }

    override fun showError() {
        getView().toastInternetConnection()
    }

    override fun showEmpty() {
        getView().showEmptyForm()
    }

    override fun showResult(arrayListRepositoriesEntity: ArrayList<UserRepositoryEntity>) {
        with(getView()) {
            setUserRepositories(ArrayListRepositoryUIMapper.map(arrayListRepositoriesEntity))
            showContainerData()
        }
    }

    override fun showCommits(repoName: String, arrayListCommitsEntity: ArrayList<RepositoryCommitEntity>) {
        getView().setCommits(RepositoryCommitsUI(repoName, ArrayListCommitUIMapper.map(arrayListCommitsEntity)))
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }

    companion object {
        private var instance: RepositoriesPresenterImpl? = null
        var firstInstance = true

        fun getInstance(): RepositoriesPresenterImpl =
            if (instance == null) {
                instance = RepositoriesPresenterImpl()
                instance as RepositoriesPresenterImpl
            } else {
                firstInstance = false
                instance as RepositoriesPresenterImpl
            }

        fun clearData() {
            instance = null
            firstInstance = true
            RepositoriesFragment.clearData()
            RepositoriesRecyclerAdapter.clearData()
        }
    }
}