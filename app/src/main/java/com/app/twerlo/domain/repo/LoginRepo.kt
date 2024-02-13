package com.app.twerlo.domain.repo

import com.app.twerlo.data.network.ApiResult
import com.app.twerlo.domain.common.ErrorState
import com.app.twerlo.domain.entity.LoginEntity

interface LoginRepo {
  suspend fun login(userName:String,password:String):ApiResult<LoginEntity,ErrorState>

}