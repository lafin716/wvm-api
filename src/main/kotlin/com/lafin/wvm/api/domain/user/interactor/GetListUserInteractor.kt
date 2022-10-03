package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class GetListUserInteractor(
  _repository: UserPersistence,
) : UseCase<GetListUserInput, GetListUserOutput> {

  val repository: UserPersistence

  init {
    repository = _repository
  }

  override fun execute(input: GetListUserInput): GetListUserOutput {
    input.validate()

    val user = repository.getList(
      UserCondition()
    )

    return if (user.isEmpty) {
      GetListUserOutput(false, "회원 정보가 없습니다.")
    } else {
      GetListUserOutput(true, "", user)
    }
  }
}

data class GetListUserInput(
  val userId: Long,
) : Input {
  override fun validate(): Boolean {
    if (userId <= 0L) {
      throw IllegalArgumentException("잘못된 유저정보 입니다.")
    }

    return true
  }
}

data class GetListUserOutput(
  val status: Boolean,
  val message: String = "",
  val app: Page<User> = Page.empty(),
) : Output