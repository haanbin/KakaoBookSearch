package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.api.RetrofitService
import com.test.kakaobooksearch.data.entities.KakaoBook
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteDataSource {

    override suspend fun getSearchBooks(queryMap: Map<String, String>): KakaoBook =
        retrofitService.getSearchBooks(queryMap)
}
