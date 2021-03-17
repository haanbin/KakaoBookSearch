package com.test.kakaobooksearch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.kakaobooksearch.data.local.dto.DocumentDto
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(documents: List<DocumentDto>)

    @Query("SELECT * FROM DOCUMENT INNER JOIN META ON META.id = DOCUMENT.metaId WHERE META.id = :metaId LIMIT :size OFFSET :start")
    suspend fun selectDocuments(metaId: Long, start: Int, size: Int): List<DocumentDto>

    @Query("DELETE FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId")
    suspend fun deleteDocumentsMetaId(metaId: Long)

    @Query("SELECT * FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId LIMIT 1 OFFSET :start")
    suspend fun selectOneDocument(metaId: Long, start: Int): DocumentDto?

    @Query("DELETE FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId AND DOCUMENT.id > :id")
    suspend fun deleteDocumentsOverId(metaId: Long, id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRx(documents: List<DocumentDto>)

    @Query("SELECT * FROM DOCUMENT INNER JOIN META ON META.id = DOCUMENT.metaId WHERE META.id = :metaId LIMIT :size OFFSET :start")
    fun selectDocumentsRx(metaId: Long, start: Int, size: Int): Single<List<DocumentDto>>

    @Query("DELETE FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId")
    fun deleteDocumentsMetaIdRx(metaId: Long)

    @Query("SELECT * FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId LIMIT 1 OFFSET :start")
    fun selectOneDocumentRx(metaId: Long, start: Int): Single<DocumentDto?>

    @Query("DELETE FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId AND DOCUMENT.id > :id")
    fun deleteDocumentsOverIdRx(metaId: Long, id: Long)
}