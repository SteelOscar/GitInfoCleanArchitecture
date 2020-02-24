package ru.steeloscar.gitinfocleanarchitecture

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceRepositoryCommit
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUpdateUserProfile
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUserProfile
import ru.steeloscar.gitinfocleanarchitecture.datasource.TestDataSourceUserRepository
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.*

class UIMapperTest {

    @Test
    fun arrayListUserProfileEntityToUI() {
        val real = ArrayListUserMapper.map(TestDataSourceUserProfile.getUsersProfileEntity())
        val expected = TestDataSourceUserProfile.getUserProfilesUI()
        assertEquals(expected, real)
    }

    @Test
    fun arrayListUserRepositoryEntityToUI() {
        val real = ArrayListRepositoryUIMapper.map(TestDataSourceUserRepository.getUserRepositoryEntities())
        val expected = TestDataSourceUserRepository.getUserRepositoriesUI()
        assertEquals(expected, real)
    }

    @Test
    fun arrayListRepositoryCommitEntityToUI() {
        val real = ArrayListCommitUIMapper.map(TestDataSourceRepositoryCommit.getRepositoryCommitEntities())
        val expected = TestDataSourceRepositoryCommit.getRepositoryCommitsUI()
        assertEquals(expected, real)
    }

    @Test
    fun updateUserProfileEntityToUI() {
        val real = UpdateUserProfileUIMapper.reverseMap(TestDataSourceUpdateUserProfile.getUpdateUserProfileEntity())
        val expected = TestDataSourceUpdateUserProfile.getUpdateUserProfileUI()
        assertEquals(expected, real)
    }

    @Test
    fun updateUserProfileUIToEntity() {
        val real = UpdateUserProfileUIMapper.forwardMap(TestDataSourceUpdateUserProfile.getUpdateUserProfileUI())
        val expected = TestDataSourceUpdateUserProfile.getUpdateUserProfileEntity()
        assertEquals(expected, real)
    }

    @Test
    fun userProfileEntityToUI() {
        val real = UserProfileUIMapper.map(TestDataSourceUserProfile.getUsersProfileEntity()[0])
        val expected = TestDataSourceUserProfile.getUserProfilesUI()[0]
        assertEquals(expected, real)
    }
}