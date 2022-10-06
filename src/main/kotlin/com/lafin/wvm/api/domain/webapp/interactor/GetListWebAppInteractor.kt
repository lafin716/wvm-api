package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import com.lafin.wvm.api.shared.type.AppPlatform
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class GetListWebAppInteractor(
  private val repository: WebAppPersistence,
) : UseCase<GetListWebAppInput, GetListWebAppOutput> {

  override fun execute(input: GetListWebAppInput): GetListWebAppOutput {
    input.validate()

    val webApp = repository.getList(condition = input.condition)

    return GetListWebAppOutput(true, "앱 정보가 조회되었습니다.", webApp)
  }
}

data class GetListWebAppInput(
  val condition: WebAppCondition,
) : Input {
  override fun validate(): Boolean {
    condition.userId ?: throw IllegalArgumentException("잘못된 유저정보 입니다.")
    if (condition.userId <= 0L) {
      throw IllegalArgumentException("잘못된 유저정보 입니다.")
    }

    return true
  }
}

data class GetListWebAppOutput(
  val status: Boolean,
  val message: String = "",
  val app: List<WebApp> = listOf(),
) : Output