package com.feature_repos.userrepos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core_ui.alert.AlertDialogWithButtons
import com.feature_repos.userrepos.model.UserReposViewState
import com.feature_repos.userrepos.ui.components.RepoListItem
import com.feature_repos.userrepos.viewmodel.UserReposViewModel

@Composable
fun UserReposScreen(
    modifier: Modifier = Modifier,
    viewModel: UserReposViewModel = hiltViewModel(),
    onItemTapped: (repoName: String, fullName: String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
        content = { paddingValues ->
            when (state) {
                is UserReposViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is UserReposViewState.Error -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AlertDialogWithButtons(
                            onReload = { viewModel.getUserRepos() }
                        )
                    }
                }

                is UserReposViewState.Result -> {
                    val userRepos = (state as UserReposViewState.Result).result
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(8.dp)
                    ) {

                        item { Spacer(modifier = Modifier.height(8.dp)) }
                        items(userRepos.userReposListModel) { item ->
                            RepoListItem(item) {
                                onItemTapped.invoke(item.repoName, userRepos.fullName)
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}

