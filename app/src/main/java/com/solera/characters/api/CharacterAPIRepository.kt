package com.solera.characters.api

import javax.inject.Inject

class CharacterAPIRepository @Inject constructor(
    private val characterAPI: CharacterAPI
) {
    fun getCharactersList() = characterAPI.getCharactersList()
    fun getStudents() = characterAPI.getStudents()
    fun getStaff() = characterAPI.getStaff()

}
