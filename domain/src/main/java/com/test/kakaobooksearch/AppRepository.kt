package com.test.kakaobooksearch

import com.test.kakaobooksearch.dto.KakaoBookDto
import com.test.kakaobooksearch.dto.ResultDto

interface AppRepository {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): ResultDto<KakaoBookDto>
}
