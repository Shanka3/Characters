package com.solera.characters.api

import com.solera.characters.model.Characters
import retrofit2.Call
import retrofit2.http.GET

interface CharacterAPI {

    @GET("characters")
    fun getCharactersList(): Call<Characters>

    @GET("characters/students")
    fun getStudents(): Call<Characters>

    @GET("characters/staff")
    fun getStaff(): Call<Characters>
}