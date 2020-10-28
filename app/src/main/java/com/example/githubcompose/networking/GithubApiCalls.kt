package com.example.githubcompose.networking

import com.example.githubcompose.utils.GITHUB_USER_ID
import retrofit2.Response
import retrofit2.http.GET

interface GithubApiCalls {

    @GET("users/$GITHUB_USER_ID/repos")
    suspend fun getRepos(): Response<List<Repos>>

}