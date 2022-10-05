package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.stereotype.Service

@Service
class GetUserByEmailInteractor(
  _repository: UserPersistence,
) : UseCase<GetUserByEmailInput, GetUserByEmailOutput> {

  final val repository: UserPersistence
  init {
    repository = _repository
  }

  override fun execute(input: GetUserByEmailInput): GetUserByEmailOutput {
    input.validate()

    val user = repository.getOne(
      UserCondition(
        email = input.email,
      )
    ) ?: return GetUserByEmailOutput(false, "회원 정보가 없습니다.")

    return GetUserByEmailOutput(true, "", user)
  }
}

data class GetUserByEmailInput(
  val email: Email,
) : Input {
  override fun validate(): Boolean {
    email.validate()
    return true
  }
}

data class GetUserByEmailOutput(
  val status: Boolean,
  val message: String = "",
  val user: User? = null,
) : Output