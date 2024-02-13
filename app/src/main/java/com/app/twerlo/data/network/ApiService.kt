package com.app.twerlo.data.network

import com.app.twerlo.data.network.dto.LoginDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

  @POST("auth/login")
  @FormUrlEncoded
  suspend fun login(@Field("username")userName:String,
                    @Field("password")password:String): Response<LoginDto>

}