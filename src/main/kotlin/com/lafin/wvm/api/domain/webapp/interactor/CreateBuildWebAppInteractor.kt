package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.BuildEventProducer
import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.usecase.CreateBuildWebAppUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import org.springframework.stereotype.Component

@Component
class CreateBuildWebAppInteractor(
  private val repository: WebAppPersistence,
  private val producer: BuildEventProducer,
) : CreateBuildWebAppUseCase<CreateBuildWebAppInput, CreateBuildWebAppOutput> {
  override fun execute(input: CreateBuildWebAppInput): CreateBuildWebAppOutput {
    input.validate()

    val condition = WebAppCondition(
      id = input.id,
      userId = input.userId,
    )
    val webApp = repository.getOne(condition) ?: return CreateBuildWebAppOutput(
      status = false,
      message = "앱을 찾을 수 없습니다.",
    )

    if (!webApp.build()) {
      return CreateBuildWebAppOutput(
        status = false,
        message = "빌드가 가능한 상태가 아닙니다.",
      )
    }

    if (!producer.requestBuild(webApp)) {
      return CreateBuildWebAppOutput(
        status = false,
        message = "빌드 요청에 실패하였습니다.",
      )
    }
    
    repository.save(webApp)
    return CreateBuildWebAppOutput(
      status = true,
      message = "빌드 요청이 완료되었습니다.",
    )
  }
}

data class CreateBuildWebAppInput(
  val id: Long,
  val userId: Long,
) : Input {
  override fun validate(): Boolean {
    return true
  }
}

data class CreateBuildWebAppOutput(
  val status: Boolean,
  val message: String = "",
) : Output