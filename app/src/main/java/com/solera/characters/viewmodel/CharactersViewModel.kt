package com.solera.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solera.characters.api.CharacterAPI
import com.solera.characters.api.CharacterAPIRepository
import com.solera.characters.model.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val characterAPIRepository: CharacterAPIRepository) :
    ViewModel() {


    private val charactersData = MutableLiveData<Characters?>()
    private val staffData = MutableLiveData<Characters?>()
    private val studentsData = MutableLiveData<Characters?>()

    val charactersLiveData: MutableLiveData<Characters?> = charactersData
    val staffLiveData: MutableLiveData<Characters?> = staffData
    val studentsLiveData: MutableLiveData<Characters?> = studentsData

    init {
        fetchCharacters()
        fetchStaff()
        fetchStudents()
    }

    fun fetchCharacters() {
        val charactersCall: Call<Characters> = characterAPIRepository.getCharactersList()
        charactersCall.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        charactersData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                charactersData.postValue(null)
            }
        })
    }


    private fun fetchStaff() {
        val charactersCall: Call<Characters> = characterAPIRepository.getStaff()
        charactersCall.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        staffData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                staffData.postValue(null)
            }
        })
    }

    private fun fetchStudents() {
        val charactersCall: Call<Characters> = characterAPIRepository.getStudents()
        charactersCall.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.isSuccessful) {
                    val charactersList = response.body()
                    charactersList?.let {
                        studentsData.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                studentsData.postValue(null)
            }
        })
    }
}