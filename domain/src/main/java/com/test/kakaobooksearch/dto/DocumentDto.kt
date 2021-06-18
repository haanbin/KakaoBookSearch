package com.test.kakaobooksearch.dto

data class DocumentDto(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    val salePrice: String,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
    var isLike: Boolean = false
)