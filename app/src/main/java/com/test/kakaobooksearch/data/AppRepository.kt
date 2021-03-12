package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.util.Constants
import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.local.LocalDataSource
import com.test.kakaobooksearch.data.remote.RemoteDataSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppDataSource {

    /**
     * 캐싱된 데이터의 입력시간이
     * Constants.NETWORK_NEED_TIME
     * 작으면 로컬 데이타
     * 크면 리모트 데이타
     */
    override suspend fun getSearchBooks(queryMap: Map<String, String>): KakaoBook {
        val keyword = queryMap[Constants.QUERY] ?: ""
        val metaDtoTimestamp = localDataSource.getMeta(keyword)?.timeStamp ?: 0
        return if (isNeedNetwork(metaDtoTimestamp)) {
            val remoteKakaoBook = remoteDataSource.getSearchBooks(queryMap)
            localDataSource.saveKakaoBook(remoteKakaoBook, keyword)
            remoteKakaoBook
        } else {
            localDataSource.getKakaoBook(keyword) ?: run {
                val remoteKakaoBook = remoteDataSource.getSearchBooks(queryMap)
                localDataSource.saveKakaoBook(remoteKakaoBook, keyword)
                remoteKakaoBook
            }
        }
    }

    /**
     * API 호출한지 Constants.NETWORK_NEED_TIME 지났으면 재호출
     */
    private fun isNeedNetwork(timestamp: Long): Boolean {
        return (System.currentTimeMillis() - timestamp) / 1000 > Constants.NETWORK_NEED_SECOND
    }

}


