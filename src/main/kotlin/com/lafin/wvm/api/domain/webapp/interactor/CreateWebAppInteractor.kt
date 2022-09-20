package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppRepository
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.domain.webapp.usecase.CreateWebAppUseCase
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service

//@Service
class CreateWebAppInteractor(
  private val repository: WebAppRepository
): CreateWebAppUseCase<CreateWebAppInput, CreateWebbAppOutput> {

  override fun execute(input: CreateWebAppInput): CreateWebbAppOutput {
    input.validate()

    if (repository.isDuplicateName(input.name)) {
      return CreateWebbAppOutput(
        result = false,
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

    val savedApp = repository.save(webApp)
    if (savedApp.id == 0L) {
      return CreateWebbAppOutput(
        result = false,
        message = "신규 앱 추가가 실패하였습니다.",
      )
    }
    
    return CreateWebbAppOutput(
      result = true,
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
  val result: Boolean,
) : Output {

  var message: String? = ""

  constructor(result: Boolean, message: String?): this(result) {
    this.message = message ?: ""
  }

  override val status: Boolean = result

}