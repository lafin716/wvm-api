package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.domain.webapp.usecase.CreateUserUseCase
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service

@Service
class CreateWebAppInteractor constructor(
  _repository: WebAppPersistence,
) : CreateUserUseCase<CreateWebAppInput, CreateWebbAppOutput> {

  val repository: WebAppPersistence

  init {
    repository = _repository
  }

  override fun execute(input: CreateWebAppInput): CreateWebbAppOutput {
    input.validate()

    val isDuplicated = repository.isDuplicateName(
      WebAppCondition(
        userId = input.userId,
        name = input.name,
      )
    )

    if (isDuplicated) {
      return CreateWebbAppOutput(
        status = false,
        message = "이미 존재하는 이름입니다.",
      )
    }

    val webApp = WebApp(
      userId = input.userId,
      name = input.name,
      initUrl = input.initUrl,
      theme = input.theme,
      platform = input.platform,
      licenseType = input.licenseType,
    )
    webApp.create()

    val savedApp = repository.save(webApp) ?: return CreateWebbAppOutput(
      status = false,
      message = "신규 앱을 추가하는 중 문제가 발생하였습니다.",
    )
    if (savedApp.id == 0L) {
      return CreateWebbAppOutput(
        status = false,
        message = "신규 앱 추가가 실패하였습니다.",
      )
    }

    return CreateWebbAppOutput(
      status = true,
      message = "신규 앱이 추가되었습니다.",
    )
  }
}

data class CreateWebAppInput(
  val userId: Long,
  val name: String,
  val initUrl: String,
  val theme: AppTheme,
  val platform: AppPlatform,
  val licenseType: LicenseType,
) : Input {
  override fun validate(): Boolean {
    return true
  }
}

data class CreateWebbAppOutput(
  val status: Boolean,
  val message: String = "",
) : Output