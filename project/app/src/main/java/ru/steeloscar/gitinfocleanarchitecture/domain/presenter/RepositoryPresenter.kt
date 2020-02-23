package ru.steeloscar.gitinfocleanarchitecture.domain.presenter

import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity

interface RepositoryPresenter {
    fun showError()
    fun showEmpty()
    fun showResult(arrayListRepositoriesEntity: ArrayList<UserRepositoryEntity>)
    fun showCommits(repoName: String,arrayListCommitsEntity: ArrayList<RepositoryCommitEntity>)
}