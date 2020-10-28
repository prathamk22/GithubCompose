package com.example.githubcompose.ui.RepoActivity

import com.example.githubcompose.networking.GithubApi
import com.example.githubcompose.networking.safeApiCall

class RepoRepository {

    suspend fun getRepos() = safeApiCall { GithubApi().api.getRepos() }

}