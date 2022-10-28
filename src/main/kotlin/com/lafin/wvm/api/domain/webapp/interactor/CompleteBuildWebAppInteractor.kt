package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.usecase.CompleteBuildWebAppUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import org.springframework.stereotype.Component

@Component
class CompleteBuildWebAppInteractor(
  private val repository: WebAppPersistence,
) : CompleteBuildWebAppUseCase<CompleteBuildWebAppInput, CompleteBuildWebAppOutput> {
  override fun execute(input: CompleteBuildWebAppInput): CompleteBuildWebAppOutput {
    input.validate()

    val condition = WebAppCondition(
      id = input.id,
      userId = input.userId,
    )
    val webApp = repository.getOne(condition) ?: return CompleteBuildWebAppOutput(
      status = false,
      message = "앱을 찾을 수 없습니다.",
    )
    webApp.complateBuild()
    repository.save(webApp)
    return CompleteBuildWebAppOutput(
      status = true,
      message = "빌드 요청이 완료되었습니다.",
    )
  }
}

data class CompleteBuildWebAppInput(
  val id: Long,
  val userId: Long,
) : Input {
  override fun validate(): Boolean {
    return true
  }
}

data class CompleteBuildWebAppOutput(
  val status: Boolean,
  val message: String = "",
) : Output