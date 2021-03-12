package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.entities.KakaoBook

interface RemoteDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): KakaoBook
}