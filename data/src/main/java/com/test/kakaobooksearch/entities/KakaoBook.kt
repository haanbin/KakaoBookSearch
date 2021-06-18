package com.test.kakaobooksearch.entities

import com.test.kakaobooksearch.dto.DocumentDto
import com.test.kakaobooksearch.dto.KakaoBookDto
import com.test.kakaobooksearch.dto.MetaDto

data class KakaoBook(
    val documents: List<Document>,
    val meta: Meta
)

fun KakaoBook.toDomain(): KakaoBookDto {
    val documentDtos = documents.map {
        DocumentDto(
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
    val mataDto = MetaDto(
        meta.totalCount,
        meta.pageableCount
    )
    return KakaoBookDto(
        documentDtos,
        mataDto
    )
}