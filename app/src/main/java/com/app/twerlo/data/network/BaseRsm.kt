package com.app.twerlo.data.network

import com.google.gson.annotations.SerializedName

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
data class BaseRsm<T>(
  @SerializedName("status")
  val status: Int,
  @SerializedName("message")
  val message: String,
  @SerializedName("data")
  val data: T?
)