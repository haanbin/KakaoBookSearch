package com.test.kakaobooksearch.entities

import com.google.gson.annotations.SerializedName
import com.test.kakaobooksearch.local.dto.MetaDto

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int
) {
    fun toMetaDto(keyword: String): MetaDto {
        return MetaDto(
            keyword = keyword,
            totalCount = totalCount,
            timeStamp = System.currentTimeMillis(),
            pageableCount = pageableCount
        )
    }
}

fun Meta.toDomain() = com.test.kakaobooksearch.dto.MetaDto(totalCount, pageableCount)
