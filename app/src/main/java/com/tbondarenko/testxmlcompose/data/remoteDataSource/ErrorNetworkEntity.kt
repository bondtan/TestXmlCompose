package com.tbondarenko.testxmlcompose.data.remoteDataSource

import com.squareup.moshi.Json
import com.tbondarenko.testxmlcompose.domain.models.ErrorDomain

data class ErrorNetworkEntity(
    @field:Json(name = "statusCode") val statusCode: Int,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "error")val error: String,
)

fun ErrorNetworkEntity.toDomain():ErrorDomain =
    ErrorDomain(
        message = "${if(this.statusCode != 0)this.statusCode else ""} ${this.message}",
        error = this.error,
    )