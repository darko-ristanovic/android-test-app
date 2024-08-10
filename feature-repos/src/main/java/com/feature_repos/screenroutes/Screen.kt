package com.feature_repos.screenroutes

sealed class Screen(val route: String) {
    data object UserReposScreen: Screen("repos_list_screen")
    data object RepoDetailsScreen: Screen("repo_details_screen")
}