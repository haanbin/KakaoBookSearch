package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook

interface AppDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): KakaoBook
}