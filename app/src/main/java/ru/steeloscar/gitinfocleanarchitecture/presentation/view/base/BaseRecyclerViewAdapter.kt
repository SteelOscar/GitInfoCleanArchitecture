package ru.steeloscar.gitinfocleanarchitecture.presentation.view.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.steeloscar.gitinfocleanarchitecture.commons.ViewHolder

abstract class BaseRecyclerViewAdapter: RecyclerView.Adapter<ViewHolder>() {

    abstract fun getLayout(): Int

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayout(), parent, false)
        return ViewHolder(view)
    }
}