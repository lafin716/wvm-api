package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.stereotype.Service

@Service
class GetUserInteractor(
  _repository: UserPersistence,
) : UseCase<GetUserInput, GetUserOutput> {

  val repository: UserPersistence

  init {
    repository = _repository
  }

  override fun execute(input: GetUserInput): GetUserOutput {
    input.validate()

    val user = repository.getOne(
      UserCondition(
        id = input.id,
      )
    ) ?: return GetUserOutput(false, "회원 정보가 없습니다.")

    return GetUserOutput(true, "", user)
  }
}

data class GetUserInput(
  val id: Long,
) : Input {
  override fun validate(): Boolean {
    if (id <= 0L) {
      throw IllegalArgumentException("유효한 앱 고유번호가 없습니다.")
    }

    return true
  }
}

data class GetUserOutput(
  val status: Boolean,
  val message: String = "",
  val app: User? = null,
) : Output