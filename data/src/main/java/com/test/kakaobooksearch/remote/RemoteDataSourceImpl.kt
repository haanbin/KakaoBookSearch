package com.test.kakaobooksearch.remote

import com.test.kakaobooksearch.api.RetrofitService
import com.test.kakaobooksearch.entities.KakaoBook
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteDataSource {

    override suspend fun getSearchBooks(queryMap: Map<String, String>): Response<KakaoBook> =
        retrofitService.getSearchBooks(queryMap)
}
