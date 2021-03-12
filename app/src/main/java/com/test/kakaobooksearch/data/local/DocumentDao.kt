package com.test.kakaobooksearch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.kakaobooksearch.data.local.dto.DocumentDto

@Dao
interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(documents: List<DocumentDto>)

    @Query("SELECT * FROM DOCUMENT INNER JOIN META ON META.id = DOCUMENT.metaId WHERE META.id = :metaId LIMIT :size OFFSET :start")
    suspend fun selectDocuments(metaId: Long, start: Int, size: Int): List<DocumentDto>

    @Query("DELETE FROM DOCUMENT WHERE DOCUMENT.metaId = :metaId")
    suspend fun deleteDocumentsMetaId(metaId: Long)
}