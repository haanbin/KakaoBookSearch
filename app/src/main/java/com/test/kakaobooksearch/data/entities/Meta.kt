package com.test.kakaobooksearch.data.entities

import com.google.gson.annotations.SerializedName
import com.test.kakaobooksearch.data.local.dto.MetaDto

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int
) {
    fun toMetaDto(keyword: String): MetaDto {
        return MetaDto(
            keyword = keyword,
            totalCount = totalCount,
            timeStamp = System.currentTimeMillis()
        )
    }
}