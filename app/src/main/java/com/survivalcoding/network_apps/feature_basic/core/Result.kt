package com.survivalcoding.network_apps.feature_basic.core

// ? extends T == out : 읽기만 가능
// ? super T == in : 쓰기만 가능
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String, val throwable: Throwable) : Result<Nothing>()
}