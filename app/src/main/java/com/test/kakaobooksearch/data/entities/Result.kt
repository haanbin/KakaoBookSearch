package com.test.kakaobooksearch.data.entities

import okhttp3.ResponseBody

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class ErrorBody(val responseBody: ResponseBody) : Result<Nothing>()
    data class Error(val message: String) : Result<Nothing>()
}