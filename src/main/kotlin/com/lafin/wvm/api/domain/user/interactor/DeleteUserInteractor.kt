package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.usecase.DeleteUserUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import org.springframework.stereotype.Service

@Service
class DeleteUserInteractor(
  _repository: UserPersistence
) : DeleteUserUseCase<DeleteUserInput, DeleteUserOutput> {

  final val repository: UserPersistence

  init {
    repository = _repository
  }

  override fun execute(input: DeleteUserInput): DeleteUserOutput {
    input.validate()

    val user = repository.getOne(
        UserCondition(
          id = input.id,
        )
    ) ?: return DeleteUserOutput(false, "탈퇴할 회원 정보가 없습니다.")
    user.removed()
    repository.save(user)

    return DeleteUserOutput(true, "탈퇴 처리되었습니다.")
  }
}

data class DeleteUserInput(
  val id: Long,
) : Input {
  override fun validate(): Boolean {
    if (id <= 0L) {
      throw IllegalArgumentException("유효한 앱 고유번호가 없습니다.")
    }
    return true
  }
}

data class DeleteUserOutput(
  val status: Boolean,
  val message: String = "",
) : Output