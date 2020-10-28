package com.example.githubcompose

import com.example.githubcompose.networking.GithubApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val response = runBlocking { GithubApi().api.getRepos() }
        assertNotNull(response)
        assertTrue(response.isSuccessful)
        assertTrue(response.body()?.size ?: 0 > 0)
    }
}