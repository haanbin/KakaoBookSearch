package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto

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
