package com.test.kakaobooksearch.local

import com.test.kakaobooksearch.local.dto.DocumentDto
import com.test.kakaobooksearch.local.dto.MetaDto
import com.test.kakaobooksearch.entities.Document
import com.test.kakaobooksearch.entities.Meta

interface LocalDataSource {

    suspend fun getMeta(keyword: String): MetaDto?

    suspend fun getDocument(metaId: Long, start: Int): DocumentDto?

    suspend fun getDocuments(metaId: Long, start: Int, size: Int): List<DocumentDto>

    suspend fun saveMeta(meta: Meta, keyword: String): Long

    suspend fun saveDocument(documents: List<Document>, metaId: Long)

    suspend fun removeMeta(keyWord: String)

    suspend fun removeDocuments(metaId: Long)

    suspend fun removeDocumentsOverId(metaId: Long, id: Long)
}
