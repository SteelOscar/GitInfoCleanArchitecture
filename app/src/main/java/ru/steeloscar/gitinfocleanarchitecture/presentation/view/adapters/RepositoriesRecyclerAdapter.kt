package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters

import ru.steeloscar.gitinfocleanarchitecture.commons.ViewHolder
import kotlinx.android.synthetic.main.repository_recycler_view_layout.view.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.*
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitsUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserRepositoryUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseRecyclerViewAdapter

class RepositoriesRecyclerAdapter private constructor(): BaseRecyclerViewAdapter() {

    private var userRepositories = ArrayList<UserRepositoryUI>()

    override fun getLayout(): Int = R.layout.repository_recycler_view_layout

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adapter = CommitsRecyclerAdapter()
        with(holder.itemView) {
            userRepositories[position].let {
                adapter.setCommits(it.commits)
                fullNameRepo.text = it.repoName
                statusRepoContainer.setStatusVisibility(arrayOf(it.forkRepo,it.archivedRepo,it.privateRepo))
                statusRepo.setStatusRepo(arrayOf(it.forkRepo,it.archivedRepo,it.privateRepo))
                starCntRepo.setValue(it.starCnt)
                watchersCntRepo.setValue(it.watchCnt)
                languageRepo.setVisibility(it.language)
                languageRepo.text = it.language
                updateRepo.setDate("Created", it.createdDate)
                createRepo.setDate("Updated", it.updateDate)
                sizeRepo.setSize(it.sizeRepo)
                commitsButtonRepo.setButtonStatus(it.visibilityCommits)
                commitsRecyclerView.setVisibility(it.visibilityCommits)
                commitsButtonRepo.isEnabled = it.commits.isNotEmpty()
                commitsButtonRepo.setOnClickListener { _ ->
                    it.visibilityCommits = !it.visibilityCommits
                    commitsButtonRepo.setButtonStatus(it.visibilityCommits)
                    commitsRecyclerView.setVisibility(it.visibilityCommits)
                }
                descriptionRepoContainer.setVisibility(it.descriptionRepo)
                descriptionRepo.text = it.descriptionRepo
            }
            commitsRecyclerView.layoutManager = CommitsLayoutManager(context)
            commitsRecyclerView.adapter = adapter
        }
    }

    override fun getItemCount() = userRepositories.size

    fun setUserRepositories(_userRepositoriesUI: ArrayList<UserRepositoryUI>) {
        userRepositories.map { userRepository ->
            _userRepositoriesUI.filter { it.repoName == userRepository.repoName }.map {
                it.visibilityCommits = userRepository.visibilityCommits
            }
        }
        userRepositories = _userRepositoriesUI
        notifyDataSetChanged()
    }

    fun setCommits(repositoryCommitsUI: RepositoryCommitsUI) {
        userRepositories.filter { it.repoName == repositoryCommitsUI.repoName }.map {
            it.commits = repositoryCommitsUI.commits
        }
        notifyDataSetChanged()
    }

    companion object {
        private var instance: RepositoriesRecyclerAdapter? = null

        fun getInstance(): RepositoriesRecyclerAdapter {
            if (instance == null) instance = RepositoriesRecyclerAdapter()
            return  instance as RepositoriesRecyclerAdapter
        }

        fun clearData() {
            instance = null
        }
    }
}