package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.stereotype.Service

@Service
class GetListWebAppInteractor(
  _repository: WebAppPersistence,
) : UseCase<GetListWebAppInput, GetListWebAppOutput> {

  val repository: WebAppPersistence

  init {
    repository = _repository
  }

  override fun execute(input: GetListWebAppInput): GetListWebAppOutput {
    input.validate()

    val webApp = repository.getList(
      WebAppCondition(
        userId = input.userId
      )
    ) ?: return GetListWebAppOutput(false, "앱 정보가 없습니다.")

    return GetListWebAppOutput(true, "앱 정보가 조회되었습니다.", webApp)
  }
}

data class GetListWebAppInput(
  val userId: Long,
) : Input {
  override fun validate(): Boolean {
    if (userId <= 0L) {
      throw IllegalArgumentException("잘못된 유저정보 입니다.")
    }

    return true
  }
}

data class GetListWebAppOutput(
  val status: Boolean,
  val message: String = "",
  val app: List<WebApp>? = null,
) : Output