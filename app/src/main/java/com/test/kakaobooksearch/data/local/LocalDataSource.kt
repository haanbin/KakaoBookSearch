package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.local.dto.MetaDto

interface LocalDataSource {

    suspend fun getMeta(keyword: String): MetaDto?

    suspend fun getKakaoBook(keyword: String): KakaoBook?

    suspend fun saveKakaoBook(kakoBook: KakaoBook, keyword: String)
}

