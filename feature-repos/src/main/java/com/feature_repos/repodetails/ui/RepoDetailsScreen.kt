package com.feature_repos.repodetails.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core_ui.alert.AlertDialogWithButtons
import com.core_ui.R
import com.feature_repos.repodetails.model.RepoDetailsViewState
import com.feature_repos.repodetails.ui.components.DetailsHeader
import com.feature_repos.repodetails.ui.components.RepoTagItem
import com.feature_repos.repodetails.viewModel.RepoDetailsViewModel

@Composable
fun RepoDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: RepoDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) { paddingValues ->

        when (state) {
            is RepoDetailsViewState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    AlertDialogWithButtons(
                        onReload = { viewModel.getRepoDetails() }
                    )
                }
            }

            RepoDetailsViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is RepoDetailsViewState.Result -> {

                val repoDetails = (state as RepoDetailsViewState.Result).result

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(8.dp)
                ) {
                    item {
                        DetailsHeader(
                            avatarUrl = repoDetails.avatarUrl,
                            fullName = repoDetails.fullName,
                            repoName = repoDetails.repoName,
                            forksCount = repoDetails.numberOfForks,
                            watchersCount = repoDetails.numberOfWatchers
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )
                    }

                    if (repoDetails.tags.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = LocalContext.current.getString(R.string.no_tags),
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    } else {
                        items(repoDetails.tags) { tag ->
                            RepoTagItem(tagModel = tag)
                        }
                    }
                }
            }
        }
    }
}