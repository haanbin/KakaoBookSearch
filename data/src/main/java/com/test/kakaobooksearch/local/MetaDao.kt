package com.test.kakaobooksearch.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.kakaobooksearch.local.dto.MetaDto

@Dao
interface MetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(metaDto: MetaDto): Long

    @Query("DELETE FROM META WHERE META.keyword = :keyword")
    suspend fun deleteMeta(keyword: String)

    @Query("SELECT * FROM META WHERE META.keyword = :keyword")
    suspend fun selectOneMeta(keyword: String): MetaDto?
}
