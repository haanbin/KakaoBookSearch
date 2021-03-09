package com.test.kakaobooksearch.data.entities

import com.google.gson.annotations.SerializedName

data class Document(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
)