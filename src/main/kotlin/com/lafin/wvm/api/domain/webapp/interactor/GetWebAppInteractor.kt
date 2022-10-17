package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase
import org.springframework.stereotype.Service

@Service
class GetWebAppInteractor(
  private val repository: WebAppPersistence,
) : UseCase<GetWebAppInput, GetWebAppOutput> {
  override fun execute(input: GetWebAppInput): GetWebAppOutput {
    input.validate()

    val webApp = repository.getOne(
      WebAppCondition(
        id = input.id,
        userId = input.userId,
      )
    ) ?: return GetWebAppOutput(false, "앱 정보가 없습니다.")

    return GetWebAppOutput(true, "앱 정보가 조회되었습니다.", webApp)
  }
}

data class GetWebAppInput(
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

data class GetWebAppOutput(
  val status: Boolean,
  val message: String = "",
  val app: WebApp? = null,
) : Output