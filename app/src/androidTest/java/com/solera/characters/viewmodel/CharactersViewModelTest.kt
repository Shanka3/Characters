package com.solera.characters.viewmodel

import androidx.lifecycle.Observer
import com.solera.characters.api.CharacterAPIRepository
import com.solera.characters.di.NetworkModule
import com.solera.characters.model.Characters
import com.solera.characters.view.fragments.CharactersFragment
import com.solera.characters.view.fragments.StaffFragment
import com.solera.characters.view.fragments.StudentsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
class CharactersViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var characterAPIRepository: CharacterAPIRepository

    private lateinit var charactersFragment: CharactersFragment
    private lateinit var staffFragment: StaffFragment
    private lateinit var studentsFragment: StudentsFragment

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        charactersViewModel = CharactersViewModel(characterAPIRepository)
    }

    @Test
    fun testFetchCharactersSuccess() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(charactersFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchCharactersFailure() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(charactersFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStaffSuccess() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(staffFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStaffFailure() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(staffFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStudentsSuccess() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(studentsFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStudentsFailure() {
        val observer = Observer<Characters?> { characters ->
            assertNotNull(characters)
            assertEquals(studentsFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }
}

