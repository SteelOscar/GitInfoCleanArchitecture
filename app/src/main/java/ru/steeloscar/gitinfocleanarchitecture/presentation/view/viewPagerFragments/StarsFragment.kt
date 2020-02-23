package ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_stars.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.configure
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.StarsPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.StarsRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView

class StarsFragment: BaseFragment<StarsPresenterImpl>(), FragmentView.Stars {

    override var presenter = StarsPresenterImpl.getInstance()

    override fun getLayout(): Int = R.layout.fragment_stars

    private var adapter = StarsRecyclerAdapter.getInstance()

    override fun initializeView() {
        if (StarsPresenterImpl.firstInstance) presenter.loadingData()
        else showContainerData()
        swipeRefreshStars.configure()
        swipeRefreshStars.setOnRefreshListener {
            presenter.refreshData()
        }
        starsRecyclerView.adapter = adapter
    }

    override fun toastInternetConnection() {
        hideProgress()
        hideRefresh()
        Toast.makeText(context, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressStars.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressStars.visibility = View.GONE
    }

    override fun showRefresh() {
        swipeRefreshStars.isRefreshing = true
    }

    override fun hideRefresh() {
        swipeRefreshStars.isRefreshing = false
    }

    override fun showContainerData() {
        hideEmptyForm()
        hideProgress()
        hideRefresh()
        starsRecyclerView.visibility = View.VISIBLE
    }

    override fun hideContainerData() {
        starsRecyclerView.visibility = View.GONE
    }

    override fun showEmptyForm() {
        hideProgress()
        hideRefresh()
        hideContainerData()
        emptyFormStars.visibility = View.VISIBLE
    }

    override fun hideEmptyForm() {
        emptyFormStars.visibility = View.GONE
    }

    override fun setUserStars(userStars: ArrayList<UserRepository>) {
        adapter.setUserStars(userStars)
    }

    companion object {
        private var instance: StarsFragment? = null

        fun getInstance(): StarsFragment {
            if (instance == null) instance = StarsFragment()
            return instance as StarsFragment
        }

        fun clearData() {
            instance = null
        }
    }
}