package com.sushkpavel.desktopleetcode.domain.model

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: Any) : ApiResult<Nothing>()
    object NetworkError : ApiResult<Nothing>()
}