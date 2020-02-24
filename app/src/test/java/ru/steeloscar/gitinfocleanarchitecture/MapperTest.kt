package ru.steeloscar.gitinfocleanarchitecture

import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.steeloscar.gitinfocleanarchitecture.data.mapper.*
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceRepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUpdateUserProfile
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUserProfile
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUserRepository

class MapperTest {
    @Test
    fun arrayListUserProfileToEntity() {
        val realList = ArrayListUserProfileEntityMapper.map(TestDataSourceUserProfile.getUsersProfile())
        val expectedList = TestDataSourceUserProfile.getUsersProfileEntity()
        assertEquals(expectedList, realList)
    }

    @Test
    fun arrayListUserRepositoryToEntity() {
        val realList = ArrayListUserRepositoryEntityMapper.map(TestDataSourceUserRepository.getUserRepositories())
        val expectedList = TestDataSourceUserRepository.getUserRepositoryEntities()
        assertEquals(expectedList, realList)
    }

    @Test
    fun arrayListRepositoryCommitToEntity() {
        val realList = ArrayListRepositoryCommitEntityMapper.map(TestDataSourceRepositoryCommit.getRepositoryCommits())
        val expectedList = TestDataSourceRepositoryCommit.getRepositoryCommitEntities()
        assertEquals(expectedList, realList)
    }

    @Test
    fun updateUserProfileBodyToEntity() {
        val real = UpdateUserProfileEntityMapper.reverseMap(TestDataSourceUpdateUserProfile.getUpdateUserProfileBody())
        val expected = TestDataSourceUpdateUserProfile.getUpdateUserProfileEntity()
        assertEquals(expected, real)
    }

    @Test
    fun updateUserProfileEntityToBody() {
        val real = UpdateUserProfileEntityMapper.forwardMap(TestDataSourceUpdateUserProfile.getUpdateUserProfileEntity())
        val expected = TestDataSourceUpdateUserProfile.getUpdateUserProfileBody()
        assertEquals(expected, real)
    }

    @Test
    fun userProfileToEntity() {
        val real = UserProfileEntityMapper.map(TestDataSourceUserProfile.getUsersProfile().get(0))
        val expected = TestDataSourceUserProfile.getUsersProfileEntity().get(0)
        assertEquals(expected, real)
    }
}