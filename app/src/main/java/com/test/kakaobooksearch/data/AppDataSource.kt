package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook
import io.reactivex.Observable

interface AppDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): KakaoBook

    fun getSearchBooksRx(
        queryMap: Map<String, String>
    ): Observable<KakaoBook>
}