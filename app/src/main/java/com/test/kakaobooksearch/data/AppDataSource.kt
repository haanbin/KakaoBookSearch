package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook
import io.reactivex.Single
import retrofit2.Response

interface AppDataSource {

    fun getSearchBooks(
        queryMap: Map<String, String>
    ): Single<Response<KakaoBook>>
}