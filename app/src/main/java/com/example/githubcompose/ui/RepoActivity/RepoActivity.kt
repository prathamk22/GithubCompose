package com.example.githubcompose.ui.RepoActivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.githubcompose.networking.Repos
import com.example.githubcompose.ui.GithubComposeTheme

class RepoActivity : AppCompatActivity() {

    val vm: RepoViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(RepoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RepoList(viewModel = vm)
                }
            }
        }
        vm.getRepos()
    }
}

@Composable
fun RepoList(viewModel: RepoViewModel) {
    val list = viewModel.repoList.observeAsState()
    val context = ContextAmbient.current
    if (list.value == null){
        Box(alignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }else{
        LazyColumnFor(items = list.value!!) {repo ->
            RepoItem(item = repo){
                Toast.makeText(context, repo.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun RepoItem(item: Repos, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            color = Color.Red
        )
        Divider()
    }
}
