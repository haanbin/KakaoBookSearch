package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook

interface AppRepository {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): KakaoBook
}