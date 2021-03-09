package com.test.kakaobooksearch.data.remote

import com.test.kakaobooksearch.data.api.RetrofitService
import com.test.kakaobooksearch.data.entities.KakaoBook
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteDataSource {

    override fun getSearchBooks(queryMap: Map<String, String>): Single<Response<KakaoBook>> =
        retrofitService.getSearchBooks(queryMap)
            .subscribeOn(Schedulers.io())
}
