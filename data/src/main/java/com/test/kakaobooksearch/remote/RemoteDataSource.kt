package com.test.kakaobooksearch.remote

import com.test.kakaobooksearch.entities.KakaoBook
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): Response<KakaoBook>
}
