package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters

import kotlinx.android.synthetic.main.stars_recycler_view_layout.view.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.*
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseRecyclerViewAdapter

class StarsRecyclerAdapter private constructor(): BaseRecyclerViewAdapter() {

    private var userStars = ArrayList<UserRepository>()
    override fun getLayout(): Int  = R.layout.stars_recycler_view_layout

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            userStars[position].let {
                fullNameStar.setValue(it.full_name)
                statusStar.setStatusRepo(statusStarContainer, arrayOf(it.private, it.archived, it.fork))
                starCntStar.setValue(it.stargazers_count)
                watchersCntStar.setValue(it.watchers_count)
                languageStar.setValue(it.language)
                createStar.setDate("Create", it.created_at)
                updateStar.setDate("Update", it.updated_at)
                sizeStar.setSize(it.size)
                descriptionStarContainer.setVisibility(it.description)
                descriptionStar.text = it.description
            }
        }
    }

    override fun getItemCount() = userStars.size

    fun setUserStars(_userStars: ArrayList<UserRepository>) {
        userStars = _userStars
        notifyDataSetChanged()
    }

    companion object {
        private var instance: StarsRecyclerAdapter? = null

        fun getInstance(): StarsRecyclerAdapter {
            if (instance == null) instance = StarsRecyclerAdapter()
            return instance as StarsRecyclerAdapter
        }

        fun clearData() {
            instance = null
        }
    }
}