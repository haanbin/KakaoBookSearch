package com.test.kakaobooksearch.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.kakaobooksearch.data.entities.Document

@Entity(tableName = "DOCUMENT")
data class DocumentDto(
    val metaId: Long,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    val salePrice: String,
    val thumbnail: String,
    val title: String,
    val isLike: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun List<DocumentDto>.toDocument(): List<Document> {
    return map {
        Document(
            contents = it.contents,
            datetime = it.datetime,
            isbn = it.isbn,
            price = it.price,
            publisher = it.publisher,
            salePrice = it.salePrice,
            thumbnail = it.thumbnail,
            title = it.title
        )
    }
}