package com.test.kakaobooksearch.entities

import com.google.gson.annotations.SerializedName
import com.test.kakaobooksearch.local.dto.DocumentDto

data class Document(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: String,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
    var isLike: Boolean = false
)

fun List<Document>.toDocumentDto(metaId: Long): List<DocumentDto> {
    return map {
        DocumentDto(
            metaId = metaId,
            authorsSplit = it.authors.joinToString("|"),
            contents = it.contents,
            datetime = it.datetime,
            isbn = it.isbn,
            isLike = false,
            price = it.price,
            publisher = it.publisher,
            salePrice = it.salePrice,
            status = it.status,
            thumbnail = it.thumbnail,
            title = it.title,
            translatorSplit = it.translators.joinToString("|"),
            url = it.url
        )
    }
}

fun List<Document>.toDomain(): List<com.test.kakaobooksearch.dto.DocumentDto> {
    return map {
        com.test.kakaobooksearch.dto.DocumentDto(
            it.authors,
            it.contents,
            it.datetime,
            it.isbn,
            it.price,
            it.publisher,
            it.salePrice,
            it.status,
            it.thumbnail,
            it.title,
            it.translators,
            it.url,
            it.isLike
        )
    }
}