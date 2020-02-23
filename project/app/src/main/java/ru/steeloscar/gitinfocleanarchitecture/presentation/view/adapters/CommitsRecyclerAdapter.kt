package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters


import kotlinx.android.synthetic.main.commits_recycler_view_layout.view.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.*
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseRecyclerViewAdapter

class CommitsRecyclerAdapter: BaseRecyclerViewAdapter() {

    private var repositoriesCommits = ArrayList<RepositoryCommitUI>()
    override fun getLayout(): Int = R.layout.commits_recycler_view_layout

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            repositoriesCommits[position].let {
                commitMessage.text = it.message
                commitAuthorAvatar.setCommitAuthorAvatar(it.committerAvatar)
                commitSha.setCommitSha(it.sha!!)
                commitDate.setCommitDate(it.committerName, it.date ?: "")
            }
        }
    }

    override fun getItemCount(): Int = repositoriesCommits.size

    fun setCommits(commits: ArrayList<RepositoryCommitUI>?) {
        if (commits != null) {
            repositoriesCommits = commits
            notifyDataSetChanged()
        }
    }
}