package ru.steeloscar.gitinfocleanarchitecture

import junit.framework.Assert.*
import io.reactivex.Observable
import org.junit.Test
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit

class MapperTest {
    @Test
    fun arrayListRepositoryCommitToEntity() {
        val list = Observable.just(ArrayList<RepositoryCommit>())
        val expectedList = Observable.just(ArrayList<RepositoryCommit>())
        assertEquals(expectedList, list)
    }
}