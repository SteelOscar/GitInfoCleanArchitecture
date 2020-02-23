package ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_repositories.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.configure
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitsUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserRepositoryUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.RepositoriesPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.RepositoriesRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView

class RepositoriesFragment: BaseFragment<RepositoriesPresenterImpl>(), FragmentView.Repositories{

    private var adapter: RepositoriesRecyclerAdapter = RepositoriesRecyclerAdapter.getInstance()

    override var presenter = RepositoriesPresenterImpl.getInstance()

    override fun getLayout(): Int = R.layout.fragment_repositories

    override fun initializeView() {
        if (RepositoriesPresenterImpl.firstInstance) presenter.loadingData()
        else showContainerData()
        repositoryRecyclerView.adapter = adapter
        swipeRefreshRepo.let {
            it.configure()
            it.setOnRefreshListener {
                showRefresh()
                presenter.refreshData()
            }
        }
    }

    override fun toastInternetConnection() {
        hideProgress()
        hideRefresh()
        Toast.makeText(context, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressRepo.visibility = View.VISIBLE
    }

    override fun showRefresh() {
        swipeRefreshRepo.isRefreshing = true
    }

    override fun showContainerData() {
        hideProgress()
        hideRefresh()
        hideEmptyForm()
        repositoryRecyclerView.visibility = View.VISIBLE
    }
    override fun showEmptyForm() {
        hideProgress()
        hideRefresh()
        hideContainerData()
        emptyFormRepo.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressRepo.visibility = View.GONE
    }

    override fun hideRefresh() {
        swipeRefreshRepo.isRefreshing = false
    }

    override fun hideContainerData() {
        repositoryRecyclerView.visibility = View.GONE
    }

    override fun hideEmptyForm() {
        emptyFormRepo.visibility = View.GONE
    }

    override fun setUserRepositories(userRepositories: ArrayList<UserRepositoryUI>) {
        adapter.setUserRepositories(userRepositories)
    }

    override fun setCommits(commits: RepositoryCommitsUI) {
        adapter.setCommits(commits)
    }

    companion object {
        private var instance: RepositoriesFragment? = null

        fun getInstance(): RepositoriesFragment {
            if (instance == null) instance = RepositoriesFragment()
            return instance as RepositoriesFragment
        }

        fun clearData() {
            instance = null
        }
    }
}