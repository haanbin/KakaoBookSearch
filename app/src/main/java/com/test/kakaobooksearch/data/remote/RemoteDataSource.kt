package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.entities.KakaoBook
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): Response<KakaoBook>
}
