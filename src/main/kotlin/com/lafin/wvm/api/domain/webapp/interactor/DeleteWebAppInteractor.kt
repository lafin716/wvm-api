package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.usecase.DeleteWebAppUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import org.springframework.stereotype.Service

@Service
class DeleteWebAppInteractor(
  _repository: WebAppPersistence
) : DeleteWebAppUseCase<DeleteWebAppInput, DeleteWebAppOutput> {

  val repository: WebAppPersistence

  init {
    repository = _repository
  }

  override fun execute(input: DeleteWebAppInput): DeleteWebAppOutput {
    input.validate()

    val webApp = repository.getOne(
        WebAppCondition(
          id = input.id,
          userId = input.userId,
        )
    ) ?: return DeleteWebAppOutput(false, "삭제할 앱 정보가 없습니다.")
    webApp.removed()
    repository.save(webApp)

    return DeleteWebAppOutput(true, "앱이 삭제 처리되었습니다.")
  }
}

data class DeleteWebAppInput(
  val id: Long,
  val userId: Long,
) : Input {
  override fun validate(): Boolean {
    if (id <= 0L) {
      throw IllegalArgumentException("유효한 앱 고유번호가 없습니다.")
    }

    if (userId <= 0L) {
      throw IllegalArgumentException("잘못된 유저정보 입니다.")
    }

    return true
  }
}

data class DeleteWebAppOutput(
  val status: Boolean,
  val message: String = "",
) : Output