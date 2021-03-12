package com.test.kakaobooksearch.data

import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.local.LocalDataSource
import com.test.kakaobooksearch.data.local.dto.MetaDto
import com.test.kakaobooksearch.data.local.dto.toDocument
import com.test.kakaobooksearch.data.remote.RemoteDataSource
import com.test.kakaobooksearch.util.Constants.DEFAULT_PAGE_VALUE
import com.test.kakaobooksearch.util.Constants.DEFAULT_SIZE_VALUE
import com.test.kakaobooksearch.util.Constants.NETWORK_NEED_SECOND
import com.test.kakaobooksearch.util.Constants.PAGE
import com.test.kakaobooksearch.util.Constants.QUERY
import com.test.kakaobooksearch.util.Constants.SIZE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        val keyword = queryMap[QUERY] ?: ""
        val size = queryMap[SIZE]?.toInt() ?: DEFAULT_SIZE_VALUE
        val page = queryMap[PAGE]?.toInt() ?: DEFAULT_PAGE_VALUE
        val metaDto = localDataSource.getMeta(keyword)
        val metaDtoTimestamp = metaDto?.timeStamp ?: 0
        return if (isNeedNetwork(metaDtoTimestamp)) {
            // 네트워크 연결이 필요한경우
            val remoteKakaoBook = remoteDataSource.getSearchBooks(queryMap)
            saveKaKaoBookInDB(keyword, page, metaDto, remoteKakaoBook)
            remoteKakaoBook
        } else {
            // DB 정보 가공
            val result = metaDto?.let {
                val list = localDataSource.getDocuments(it.id, getStart(page, size), size)
                KakaoBook(list.toDocument(), it.toMeta())
            }
            // list 비어있을 경우 remote 호출
            if (result != null && result.documents.isNotEmpty()) {
                result
            } else {
                val remoteKakaoBook = remoteDataSource.getSearchBooks(queryMap)
                saveKaKaoBookInDB(keyword, page, metaDto, remoteKakaoBook)
                remoteKakaoBook
            }
        }
    }

    /**
     * 로컬 DB 데이터 저장
     */
    private suspend fun saveKaKaoBookInDB(
        keyword: String,
        page: Int,
        metaDto: MetaDto?,
        kakaoBook: KakaoBook
    ) =
        GlobalScope.launch {
            if (page == 1) {
                localDataSource.removeMeta(keyword)
                val metaId = localDataSource.saveMeta(kakaoBook.meta, keyword)
                localDataSource.removeDocuments(metaId)
                localDataSource.saveDocument(kakaoBook.documents, metaId)
            } else {
                metaDto?.let {
                    localDataSource.saveDocument(kakaoBook.documents, it.id)
                }
            }
        }


    /**
     * API 호출한지 Constants.NETWORK_NEED_TIME 지났으면 재호출
     */
    private fun isNeedNetwork(timestamp: Long): Boolean {
        return (System.currentTimeMillis() - timestamp) / 1000 > NETWORK_NEED_SECOND
    }

    /**
     * 시작 카운트 세기
     */
    fun getStart(page: Int, size: Int): Int = (page - 1) * size


}


