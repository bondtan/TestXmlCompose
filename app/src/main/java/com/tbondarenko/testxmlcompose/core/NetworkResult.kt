package com.tbondarenko.testxmlcompose.core

sealed class NetworkResult<T: Any> {
    class Success<T: Any>(val data: T): NetworkResult<T>()
    class Error<T: Any>(val statusCode:Int, val message: String, val error: String): NetworkResult<T>()
    class Loading<T:Any>: NetworkResult<T>()
}
