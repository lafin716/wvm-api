package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.domain.webapp.usecase.UpdateUserUseCase
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service

@Service
class UpdateUserInteractor(
  _repository: UserPersistence,
) : UpdateUserUseCase<UpdateUserInput, UpdateUserOutput> {

  final val repository: UserPersistence

  init {
    repository = _repository
  }

  override fun execute(input: UpdateUserInput): UpdateUserOutput {
    input.validate()

    val user = repository.getOne(
      UserCondition(
        id = input.id,
      )
    ) ?: return UpdateUserOutput(
      status = false,
      message = "회원 정보가 없습니다."
    )

    if (input.name != null) {
      user.updateName(input.name)
    }

    if (input.password != null) {
      user.changePassword(input.password)
    }

    repository.save(user)
    return UpdateUserOutput(
      status = true,
      message = "회원정보가 정상적으로 업데이트 되었습니다.",
    )
  }
}

data class UpdateUserInput(
  val id: Long,
  val name: String?,
  val password: Password?,
) : Input {
  override fun validate(): Boolean {
    if (id == 0L) {
      throw IllegalArgumentException("유효한 고유번호를 입력하세요.")
    }
    password?.validate()

    return true
  }
}

data class UpdateUserOutput(
  val status: Boolean,
  val message: String = "",
) : Output
