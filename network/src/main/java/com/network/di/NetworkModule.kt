package com.network.di

import com.network.apiservice.GitHubApiService
import com.network.datasource.IReposDataSource
import com.network.datasource.ReposDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    abstract fun bindReposDataSource(
        reposDataSource: ReposDataSource
    ): IReposDataSource

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideGitGubApiService(retrofit: Retrofit): GitHubApiService {
            return retrofit.create(GitHubApiService::class.java)
        }
    }
}