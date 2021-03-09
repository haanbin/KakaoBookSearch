package com.test.kakaobooksearch.di

import com.test.kakaobooksearch.data.AppDataSource
import com.test.kakaobooksearch.data.AppRepository
import com.test.kakaobooksearch.data.local.LocalDataSource
import com.test.kakaobooksearch.data.local.LocalDataSourceImpl
import com.test.kakaobooksearch.data.remote.RemoteDataSource
import com.test.kakaobooksearch.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule{

    @Binds
    abstract fun bindAppRepository(appRepository: AppRepository): AppDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

}