package com.test.kakaobooksearch

import com.test.kakaobooksearch.data.local.dto.MetaDto
import com.test.kakaobooksearch.data.local.dto.toDocument
import com.test.kakaobooksearch.dto.KakaoBookDto
import com.test.kakaobooksearch.entities.KakaoBook
import com.test.kakaobooksearch.entities.Result
import com.test.kakaobooksearch.entities.toDomain
import com.test.kakaobooksearch.local.LocalDataSource
import com.test.kakaobooksearch.remote.RemoteDataSource
import com.test.kakaobooksearch.util.Constants
import com.test.kakaobooksearch.util.Constants.RESULT_ERROR_BODY_NULL
import com.test.kakaobooksearch.util.Constants.RESULT_ERROR_NULL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppRepository {

    /**
     * 캐싱된 데이터의 입력시간이
     * Constants.NETWORK_NEED_TIME
     * 작으면 로컬 데이타
     * 크면 리모트 데이타
     */
    override suspend fun getSearchBooks(queryMap: Map<String, String>): Result<KakaoBookDto> {
        val keyword = queryMap[Constants.QUERY] ?: ""
        val size = queryMap[Constants.SIZE]?.toInt() ?: Constants.DEFAULT_SIZE_VALUE
        val page = queryMap[Constants.PAGE]?.toInt() ?: Constants.DEFAULT_PAGE_VALUE
        val metaDto = localDataSource.getMeta(keyword)
        val metaDtoTimestamp = metaDto?.timeStamp ?: 0
        return if (isNeedNetwork(metaDtoTimestamp)) {
            // 네트워크 연결이 필요한 경우
            getKakaoBookInApi(queryMap, keyword, page, size, metaDto)
        } else {
            // DB 정보 사용할 경우
            // DB 정보 가공
            val result = metaDto?.let {
                val list = localDataSource.getDocuments(it.id, getStart(page, size), size)
                KakaoBook(list.toDocument(), it.toMeta()).toDomain()
            }
            // list 비어있을 경우 remote 호출
            if (result != null && result.documents.isNotEmpty()) {
                Result.Success(result)
            } else {
                // 네트워크 연결이 필요한 경우
                getKakaoBookInApi(queryMap, keyword, page, size, metaDto)
            }
        }
    }

    /**
     * Remote 데이타 처리
     */
    private suspend fun getKakaoBookInApi(
        queryMap: Map<String, String>,
        keyword: String,
        page: Int,
        size: Int,
        metaDto: MetaDto?
    ): Result<KakaoBook> {
        val remoteKakaoBook = remoteDataSource.getSearchBooks(queryMap)
        if (remoteKakaoBook.isSuccessful) {
            remoteKakaoBook.body()?.let {
                if (it.meta.pageableCount != 0) {
                    saveKaKaoBookInDB(keyword, page, size, metaDto, it)
                }
                return Result.Success(it)
            } ?: return Result.Error(RESULT_ERROR_NULL)
        } else {
            remoteKakaoBook.errorBody()?.let {
                return Result.ErrorBody(it)
            } ?: return Result.Error(RESULT_ERROR_BODY_NULL)
        }
    }

    /**
     * 로컬 DB 데이터 저장
     */
    private suspend fun saveKaKaoBookInDB(
        keyword: String,
        page: Int,
        size: Int,
        metaDto: MetaDto?,
        kakaoBook: KakaoBook
    ) =
        CoroutineScope(Dispatchers.IO).launch {
            if (page == 1) {
                localDataSource.removeMeta(keyword)
                metaDto?.let {
                    localDataSource.removeDocuments(it.id)
                }
                val metaId = localDataSource.saveMeta(kakaoBook.meta, keyword)
                localDataSource.saveDocument(kakaoBook.documents, metaId)
            } else {
                metaDto?.let {
                    val start = if (getStart(page, size) == 0) {
                        0
                    } else {
                        getStart(page, size) - 1
                    }
                    val documentDto = localDataSource.getDocument(it.id, start)
                    documentDto?.let { documentDtoNotnull ->
                        localDataSource.removeDocumentsOverId(it.id, documentDtoNotnull.id)
                    }
                    localDataSource.saveDocument(kakaoBook.documents, it.id)
                }
            }
        }

    /**
     * API 호출한지 Constants.NETWORK_NEED_TIME 지났으면 재호출
     */
    private fun isNeedNetwork(timestamp: Long): Boolean {
        return (System.currentTimeMillis() - timestamp) / 1000 > Constants.NETWORK_NEED_SECOND
    }

    /**
     * 시작 카운트 세기
     */
    private fun getStart(page: Int, size: Int): Int = (page - 1) * size
}
