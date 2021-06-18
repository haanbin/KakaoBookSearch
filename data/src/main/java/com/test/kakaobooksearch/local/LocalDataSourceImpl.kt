package com.test.kakaobooksearch.local

import com.test.kakaobooksearch.local.dto.DocumentDto
import com.test.kakaobooksearch.local.dto.MetaDto
import com.test.kakaobooksearch.entities.Document
import com.test.kakaobooksearch.entities.Meta
import com.test.kakaobooksearch.entities.toDocumentDto
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

    override suspend fun getDocument(metaId: Long, start: Int): DocumentDto? {
        return documentDao.selectOneDocument(metaId, start)
    }

    override suspend fun getDocuments(metaId: Long, start: Int, size: Int): List<DocumentDto> {
        return documentDao.selectDocuments(metaId, start, size)
    }

    override suspend fun saveMeta(meta: Meta, keyword: String): Long {
        return metaDao.insertMeta(meta.toMetaDto(keyword))
    }

    override suspend fun saveDocument(documents: List<Document>, metaId: Long) {
        documentDao.insertAll(documents.toDocumentDto(metaId))
    }

    override suspend fun removeMeta(keyWord: String) {
        metaDao.deleteMeta(keyWord)
    }

    override suspend fun removeDocuments(metaId: Long) {
        documentDao.deleteDocumentsMetaId(metaId)
    }

    override suspend fun removeDocumentsOverId(metaId: Long, id: Long) {
        documentDao.deleteDocumentsOverId(metaId, id)
    }
}
