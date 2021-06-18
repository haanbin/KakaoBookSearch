package com.test.kakaobooksearch.api

import com.test.kakaobooksearch.entities.KakaoBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET("v3/search/book")
    suspend fun getSearchBooks(
        @QueryMap
        queryMap: Map<String, String>
    ): Response<KakaoBook>
}
