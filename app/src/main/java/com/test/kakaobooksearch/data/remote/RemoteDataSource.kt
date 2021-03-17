package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.entities.KakaoBook
import io.reactivex.Observable

interface RemoteDataSource {

    suspend fun getSearchBooks(
        queryMap: Map<String, String>
    ): KakaoBook

    fun getSearchBooksRx(
        queryMap: Map<String, String>
    ): Observable<KakaoBook>
}