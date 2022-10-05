package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.stereotype.Service

@Service
class SignInUserInteractor(
  private val repository: UserPersistence,
) : UseCase<SignInUserInput, SignInUserOutput> {

  override fun execute(input: SignInUserInput): SignInUserOutput {
    input.validate()

    val user = repository.getByEmailAndPassword(
      email = input.email,
      password = input.password,
    ) ?: return SignInUserOutput(false, "회원 정보가 없습니다.")

    return SignInUserOutput(true, "", user)
  }
}

data class SignInUserInput(
  val email: Email,
  val password: Password,
) : Input {
  override fun validate(): Boolean {
    email.validate()
    password.validate()

    return true
  }
}

data class SignInUserOutput(
  val status: Boolean,
  val message: String = "",
  val user: User? = null,
) : Output