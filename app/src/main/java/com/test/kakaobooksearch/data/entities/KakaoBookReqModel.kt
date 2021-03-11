package com.test.kakaobooksearch.data.entities

import com.test.kakaobooksearch.base.Constants

data class KakaoBookReqModel(
    var query: String = "",
    val size: Int = 50,
    var page: Int = 1
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            Constants.QUERY to query,
            Constants.PAGE to page.toString(),
            Constants.SIZE to size.toString()
        )
    }
}