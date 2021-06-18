package com.test.kakaobooksearch.di

import com.test.kakaobooksearch.AppRepository
import com.test.kakaobooksearch.AppRepositoryImpl
import com.test.kakaobooksearch.local.LocalDataSource
import com.test.kakaobooksearch.local.LocalDataSourceImpl
import com.test.kakaobooksearch.remote.RemoteDataSource
import com.test.kakaobooksearch.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindAppRepository(appRepository: AppRepositoryImpl): AppRepository

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}
