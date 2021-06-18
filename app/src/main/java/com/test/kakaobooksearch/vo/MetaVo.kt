package com.test.kakaobooksearch.vo

import com.test.kakaobooksearch.dto.MetaDto

data class MetaVo(
    val totalCount: Int,
    val pageableCount: Int
)

fun MetaDto.toPresenter() = MetaVo(totalCount, pageableCount)