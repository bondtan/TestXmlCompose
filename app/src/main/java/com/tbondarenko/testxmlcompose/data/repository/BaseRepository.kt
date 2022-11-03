package com.tbondarenko.testxmlcompose.data.repository

import android.util.Log
import com.squareup.moshi.Moshi
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.remoteDataSource.ErrorNetworkEntity
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        doSomethingInSuccess: (suspend(T) -> Unit)? = null,
        apiToBeCalled: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response: Response<T> = apiToBeCalled()
            if (response.isSuccessful) {
                val body = response.body()!!
                doSomethingInSuccess?.invoke(body)
                NetworkResult.Success(data = body)
            } else {
                val errorResponse: ErrorNetworkEntity? =
                    convertErrorBody(response.errorBody())
                Log.d("Repos", "$errorResponse")
                NetworkResult.Error(
                    statusCode = errorResponse?.statusCode ?: 0,
                    message = errorResponse?.message ?: "Something went wrong",
                    error = errorResponse?.error ?: "Not found"
                )
            }
        } catch (e: HttpException) {
            NetworkResult.Error(
                message = e.message ?: "Something went wrong",
                error = "Error",
                statusCode = 0
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                message = "Please check your network connection",
                error = "Error",
                statusCode = 0
            )
        } catch (e: Exception) {
            NetworkResult.Error(message = "Something went wrong", error = "Error", statusCode = 0)
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorNetworkEntity? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorNetworkEntity::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}