package com.test.kakaobooksearch

import com.test.kakaobooksearch.dto.KakaoBookDto

interface AppRepository {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): Result<KakaoBookDto>
}
