package com.example.data.di

import com.example.data.ExampleRemoteMediator
import com.example.data.MovieRepositoryImpl
import com.example.data.database.MovieDatabase
import com.example.data.network.MovieService
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieService,
                               local: MovieDatabase,
                               exampleRemoteMediator: ExampleRemoteMediator
    ): MovieRepository {
        return MovieRepositoryImpl(api,local,exampleRemoteMediator)
    }
}