package com.test.kakaobooksearch.data.entities

data class KakaoBookReqModel(
    var query: String = "",
    val size: Int = 50,
    var page: Int = 1
)