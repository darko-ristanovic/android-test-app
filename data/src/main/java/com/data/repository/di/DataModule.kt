package com.data.repository.di

import com.data.repository.githubrepos.GitHubRepository
import com.data.repository.githubrepos.IGitHubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindGitHubRepository(
        gitHubRepository: GitHubRepository
    ): IGitHubRepository
}