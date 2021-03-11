package com.test.kakaobooksearch.data.entities

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("total_count")
    val totalCount: Int
)