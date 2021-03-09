package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.local.LocalDataSource
import com.test.kakaobooksearch.data.remote.RemoteDataSource
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    AppDataSource {

    override fun getSearchBooks(queryMap: Map<String, String>): Single<Response<KakaoBook>> =
        remoteDataSource.getSearchBooks(queryMap)
}