package com.test.kakaobooksearch.vo

import com.test.kakaobooksearch.dto.KakaoBookDto

data class KakaoBookVo(
    val documents: List<DocumentVo>,
    val meta: MetaVo
)

fun KakaoBookDto.toPresenter() = KakaoBookVo(documents.toPresenter(), meta.toPresenter())

