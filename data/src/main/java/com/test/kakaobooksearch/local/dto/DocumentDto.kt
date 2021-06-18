package com.test.kakaobooksearch.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.kakaobooksearch.entities.Document

@Entity(tableName = "DOCUMENT")
data class DocumentDto(
    val metaId: Long,
    val authorsSplit: String,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    val salePrice: String,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translatorSplit: String,
    val url: String,
    val isLike: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun List<DocumentDto>.toDocument(): List<Document> {
    return map {
        Document(
            authors = it.authorsSplit.split("|"),
            contents = it.contents,
            datetime = it.datetime,
            isbn = it.isbn,
            price = it.price,
            publisher = it.publisher,
            salePrice = it.salePrice,
            status = it.status,
            thumbnail = it.thumbnail,
            title = it.title,
            translators = it.translatorSplit.split("|"),
            url = it.url
        )
    }
}
