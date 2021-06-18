package com.test.kakaobooksearch.entities

import com.test.kakaobooksearch.util.Constants

data class KakaoBookReqModel(
    var query: String = "",
    val size: Int = Constants.DEFAULT_SIZE_VALUE,
    var page: Int = Constants.DEFAULT_PAGE_VALUE
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            Constants.QUERY to query,
            Constants.PAGE to page.toString(),
            Constants.SIZE to size.toString()
        )
    }
}
