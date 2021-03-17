package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface LocalDataSource {

    suspend fun getMeta(keyword: String): MetaDto?

    suspend fun getDocument(metaId: Long, start: Int): DocumentDto?

    suspend fun getDocuments(metaId: Long, start: Int, size: Int): List<DocumentDto>

    suspend fun saveMeta(meta: Meta, keyword: String): Long

    suspend fun saveDocument(documents: List<Document>, metaId: Long)

    suspend fun removeMeta(keyWord: String)

    suspend fun removeDocuments(metaId: Long)

    suspend fun removeDocumentsOverId(metaId: Long, id: Long)

    fun getMetaRx(keyword: String): Observable<MetaDto?>

    fun getDocumentRx(metaId: Long, start: Int): Observable<DocumentDto?>

    fun getDocumentsRx(metaId: Long, start: Int, size: Int): Observable<List<DocumentDto>>

    fun saveMetaRx(meta: Meta, keyword: String): Observable<Long>

    fun saveDocumentRx(documents: List<Document>, metaId: Long): Completable

    fun removeMetaRx(keyWord: String): Completable

    fun removeDocumentsRx(metaId: Long): Completable

    fun removeDocumentsOverIdRx(metaId: Long, id: Long): Completable
}

