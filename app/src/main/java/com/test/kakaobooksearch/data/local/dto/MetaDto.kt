package com.test.kakaobooksearch.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.kakaobooksearch.data.entities.Meta

@Entity(tableName = "META")
data class MetaDto(
    val keyword: String ="",
    val totalCount: Int = 0,
    val pageableCount: Int = 0,
    val timeStamp: Long = 0
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