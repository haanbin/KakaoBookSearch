package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.entities.toDocumentDto
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val metaDao: MetaDao,
    private val documentDao: DocumentDao
) : LocalDataSource {

    override suspend fun saveMeta(meta: Meta, keyword: String): Long {
        return metaDao.insertMeta(meta.toMetaDto(keyword))
    }

    override suspend fun saveDocument(documents: List<Document>, metaId: Long) {
        documentDao.insertAll(documents.toDocumentDto(metaId))
    }

    override suspend fun remoteMeta(keyWord: String) {
        metaDao.deleteMeta(keyWord)
    }

    override suspend fun remoteDocuments(metaId: Long) {
        documentDao.deleteDocumentsMetaId(metaId)
    }

    override suspend fun getMeta(keyword: String): MetaDto {
        return metaDao.selectOneMeta(keyword)
    }

    override suspend fun getDocuments(metaId: Long): List<DocumentDto> {
        return documentDao.selectDocuments(metaId)
    }
}