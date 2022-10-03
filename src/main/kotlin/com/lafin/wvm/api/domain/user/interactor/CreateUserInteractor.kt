package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.domain.webapp.usecase.CreateUserUseCase
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service

@Service
class CreateUserInteractor constructor(
  _repository: UserPersistence,
) : CreateUserUseCase<CreateUserInput, CreateUserOutput> {

  val repository: UserPersistence

  init {
    repository = _repository
  }

  override fun execute(input: CreateUserInput): CreateUserOutput {
    input.validate()

    val isDuplicated = repository.isDuplicateEmail(
      UserCondition(
        email = input.email,
      )
    )

    if (isDuplicated) {
      return CreateUserOutput(
        status = false,
        message = "이미 가입 된 이메일입니다.",
      )
    }

    val user = User(
      name = input.name,
      email = input.email,
      password = input.password,
    )
    user.registed()

    val savedApp = repository.save(user) ?: return CreateUserOutput(
      status = false,
      message = "회원가입 중 문제가 발생하였습니다.",
    )
    if (savedApp.id == 0L) {
      return CreateUserOutput(
        status = false,
        message = "회원가입이 실패하였습니다.",
      )
    }

    return CreateUserOutput(
      status = true,
      message = "회원가입이 완료되었습니다.",
    )
  }
}

data class CreateUserInput(
  val name: String,
  val email: Email,
  val password: Password,
) : Input {
  override fun validate(): Boolean {
    email.validate()
    password.validate()
    return true
  }
}

data class CreateUserOutput(
  val status: Boolean,
  val message: String = "",
) : Output