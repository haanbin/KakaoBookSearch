package com.test.kakaobooksearch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.kakaobooksearch.data.local.dto.MetaDto
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MetaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(metaDto: MetaDto): Long

    @Query("DELETE FROM META WHERE META.keyword = :keyword")
    suspend fun deleteMeta(keyword: String)

    @Query("SELECT * FROM META WHERE META.keyword = :keyword")
    suspend fun selectOneMeta(keyword: String): MetaDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMetaRx(metaDto: MetaDto): Single<Long>

    @Query("DELETE FROM META WHERE META.keyword = :keyword")
    fun deleteMetaRx(keyword: String)

    @Query("SELECT * FROM META WHERE META.keyword = :keyword")
    fun selectOneMetaRx(keyword: String): Single<MetaDto?>
}