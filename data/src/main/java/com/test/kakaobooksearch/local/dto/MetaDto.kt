package com.test.kakaobooksearch.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.kakaobooksearch.entities.Meta

@Entity(tableName = "META")
data class MetaDto(
    val keyword: String,
    val totalCount: Int,
    val pageableCount: Int,
    val timeStamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toMeta(): Meta {
        return Meta(
            totalCount = totalCount,
            pageableCount = pageableCount
        )
    }
}
