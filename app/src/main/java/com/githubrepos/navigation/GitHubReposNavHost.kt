package com.githubrepos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.feature_repos.repodetails.ui.RepoDetailsScreen
import com.feature_repos.screenroutes.Screen
import com.feature_repos.userrepos.model.Constants
import com.feature_repos.userrepos.ui.UserReposScreen

@Composable
fun GitHubReposNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserReposScreen.route
    ) {
        composable(
            route = Screen.UserReposScreen.route
        ) {
            UserReposScreen { repoName, fullName ->
                navController.navigate(
                    Screen.RepoDetailsScreen.route + "/${repoName}/${fullName}"
                )
            }
        }
        composable(
            route = Screen.RepoDetailsScreen.route + "/{${Constants.PARAM_REPO_NAME}}/{${Constants.PARAM_FULL_NAME}}"
        ) {
            RepoDetailsScreen()
        }
    }
}