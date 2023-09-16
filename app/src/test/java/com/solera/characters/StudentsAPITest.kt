package com.solera.characters

import com.solera.characters.api.CharacterAPI
import com.solera.characters.model.Characters
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentsAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var characterAPI: CharacterAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        characterAPI = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/")).build().create(CharacterAPI::class.java)
    }

    @Test
    fun testStudentsListAndReturnSuccess() {

        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val call: Call<Characters> = characterAPI.getStudents()
        val response = call.execute()

        runBlocking {
            mockWebServer.takeRequest()
            assert(response.isSuccessful)
            assert(response.body() != null)
        }
    }

    @Test
    fun testStudentsListAndReturnError() {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Error fetching data from api")
        mockWebServer.enqueue(mockResponse)

        val call: Call<Characters> = characterAPI.getStudents()
        val response = call.execute()

        runBlocking {
            mockWebServer.takeRequest()
            assert(!response.isSuccessful)
            Assert.assertEquals(404, response.code())
        }
    }

    @After
    fun tearDown() {
        // Shutdown the mock web server
        mockWebServer.shutdown()
    }
}