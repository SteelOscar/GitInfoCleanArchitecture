package ru.steeloscar.gitinfocleanarchitecture

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.steeloscar.gitinfocleanarchitecture.data.mapper.ArrayListUserProfileEntityMapper
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUserProfile
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.RepositoryCommitEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserRepositoryEntity

class MapperTest {
    @Test
    fun arrayListUserProfileToEntity() {
        val realList = ArrayListUserProfileEntityMapper.map(TestDataSourceUserProfile.getUsersProfile())
        val expectedList = TestDataSourceUserProfile.getUsersProfileEntity()
        assertEquals(expectedList::class.java, realList::class.java)
    }

    @Test
    fun arrayListUserRepositoryToEntity() {
        val list = ArrayList<UserRepository>()
        val expectedList = ArrayList<UserRepositoryEntity>()
    }

    @Test
    fun arrayListRepositoryCommitToEntity() {
        val list = ArrayList<RepositoryCommit>()
        val expectedList = ArrayList<RepositoryCommitEntity>()
    }

    @Test
    fun updateUserProfileBodyToEntityMapper() {

    }
}