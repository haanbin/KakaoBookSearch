package com.test.kakaobooksearch.data.local

import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.entities.toDocumentDto
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import com.test.kakaobooksearch.data.local.dto.MetaDto
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
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

    override fun getMetaRx(keyword: String): Observable<MetaDto?> {
        return metaDao.selectOneMetaRx(keyword)
            .doOnError {
                Timber.d("doOnError : $it")
            }
            .toObservable()
    }

    override fun getDocumentRx(metaId: Long, start: Int): Observable<DocumentDto?> {
        return documentDao.selectOneDocumentRx(metaId, start).toObservable()
    }

    override fun getDocumentsRx(
        metaId: Long,
        start: Int,
        size: Int
    ): Observable<List<DocumentDto>> {
        return documentDao.selectDocumentsRx(metaId, start, size).toObservable()
    }

    override fun saveMetaRx(meta: Meta, keyword: String): Observable<Long> {
        return metaDao.insertMetaRx(meta.toMetaDto(keyword)).toObservable()
    }

    override fun saveDocumentRx(documents: List<Document>, metaId: Long): Completable {
        return Completable.fromCallable { documentDao.insertAllRx(documents.toDocumentDto(metaId)) }
    }

    override fun removeMetaRx(keyWord: String): Completable {
        return Completable.fromCallable { metaDao.deleteMetaRx(keyWord) }

    }

    override fun removeDocumentsRx(metaId: Long): Completable {
        return Completable.fromCallable { documentDao.deleteDocumentsMetaIdRx(metaId) }
    }

    override fun removeDocumentsOverIdRx(metaId: Long, id: Long): Completable {
        return Completable.fromCallable { documentDao.deleteDocumentsOverIdRx(metaId, id) }
    }
}