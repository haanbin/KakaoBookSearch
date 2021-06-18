package com.test.kakaobooksearch.entities

import com.test.kakaobooksearch.dto.KakaoBookDto

data class KakaoBook(
    val documents: List<Document>,
    val meta: Meta
)

fun KakaoBook.toDomain(): KakaoBookDto {
    return KakaoBookDto(
        documents.toDomain(),
        meta.toDomain()
    )
}