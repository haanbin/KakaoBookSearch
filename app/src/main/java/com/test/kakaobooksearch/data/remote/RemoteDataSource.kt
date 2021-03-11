package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.entities.KakaoBook
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSource {

    fun getSearchBooks(
        queryMap: Map<String, String>
    ): Single<KakaoBook>
}