package com.test.kakaobooksearch.dto

import okhttp3.ResponseBody

sealed class ResultDto<out R> {
    data class Success<out T>(val data: T) : ResultDto<T>()
    data class ErrorBody(val responseBody: ResponseBody) : ResultDto<Nothing>()
    data class Error(val message: String) : ResultDto<Nothing>()
}
