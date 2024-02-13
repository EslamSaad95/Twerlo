package com.app.twerlo.domain.userCase

import com.app.twerlo.domain.repo.LoginRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepo) {

  suspend fun login(userName:String,password:String) = flow {
    emit(repository.login(userName, password))
  }
}