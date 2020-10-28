package com.example.githubcompose.ui.RepoActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubcompose.networking.Repos
import com.example.githubcompose.networking.ResultWrapper
import kotlinx.coroutines.launch

class RepoViewModel(
    private val repo: RepoRepository = RepoRepository()
) : ViewModel() {

    private val _repos = MutableLiveData<List<Repos>>()
    val repoList: LiveData<List<Repos>> = _repos

    fun getRepos() {
        viewModelScope.launch {
            when (val response = repo.getRepos()) {
                is ResultWrapper.GenericError -> {
                }
                is ResultWrapper.Success -> {
                    if (response.value.isSuccessful) {
                        _repos.postValue(response.value.body())
                    } else {
                    }
                }
            }
        }
    }

}