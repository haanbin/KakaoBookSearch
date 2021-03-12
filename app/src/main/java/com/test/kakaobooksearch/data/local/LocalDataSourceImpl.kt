package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.entities.toDocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto
import com.test.kakaobooksearch.data.local.dto.toDocument
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val metaDao: MetaDao,
    private val documentDao: DocumentDao
) : LocalDataSource {

    override suspend fun getMeta(keyword: String): MetaDto? {
        return metaDao.selectOneMeta(keyword)
    }

    override suspend fun getKakaoBook(keyword: String): KakaoBook? {
        val metaDto = metaDao.selectOneMeta(keyword)
        return metaDto?.let {
            val documentDtoList = documentDao.selectDocuments(it.id)
            return KakaoBook(documentDtoList.toDocument(), it.toMeta())
        }
    }

    override suspend fun saveKakaoBook(kakoBook: KakaoBook, keyword: String) {
        metaDao.deleteMeta(keyword)
        val metaId = metaDao.insertMeta(kakoBook.meta.toMetaDto(keyword))
        documentDao.deleteDocumentsMetaId(metaId)
        documentDao.insertAll(kakoBook.documents.toDocumentDto(metaId))
    }
}