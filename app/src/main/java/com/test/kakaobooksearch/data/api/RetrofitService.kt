package com.test.kakaobooksearch.data.api

import com.test.kakaobooksearch.data.entities.KakaoBook
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RetrofitService {

    @GET("v3/search/book")
    suspend fun getSearchBooks(
        @QueryMap
        queryMap: Map<String, String>
    ): KakaoBook

}