package com.app.twerlo.data.mapper

import com.app.twerlo.data.network.dto.LoginDto
import com.app.twerlo.domain.entity.LoginEntity

fun LoginDto.toLoginEntity():LoginEntity{
  return LoginEntity(this.token)
}