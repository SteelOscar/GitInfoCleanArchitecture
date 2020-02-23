package ru.steeloscar.gitinfocleanarchitecture.presentation.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter

abstract class BaseFragment<P: BasePresenter<Any?>>: Fragment() {

    abstract var presenter: P

    abstract fun getLayout(): Int
    abstract fun initializeView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.attachView(this)
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

}