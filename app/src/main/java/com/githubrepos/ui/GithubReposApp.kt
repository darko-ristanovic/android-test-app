package com.githubrepos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.githubrepos.navigation.GitHubReposNavHost

@Composable
fun GithubReposApp(navController: NavHostController) {
    GitHubReposNavHost(navController = navController)
}