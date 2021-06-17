package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.entities.Result

interface AppRepository {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): Result<KakaoBook>
}
