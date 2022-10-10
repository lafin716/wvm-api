package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.gateway.WebAppStorage
import com.lafin.wvm.api.domain.webapp.usecase.UpdateUserUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UpdateWebAppInteractor(
  private val repository: WebAppPersistence,
  private val storage: WebAppStorage,
) : UpdateUserUseCase<UpdateWebAppInput, UpdateWebAppOutput> {

  override fun execute(input: UpdateWebAppInput): UpdateWebAppOutput {
    input.validate()

    val webApp = repository.getOne(
      WebAppCondition(
        id = input.id,
        userId = input.userId,
      )
    ) ?: return UpdateWebAppOutput(
      status = false,
      message = "앱 정보가 없습니다."
    )

    if (input.name != null) {
      webApp.updateAppName(input.name)
    }

    if (input.theme != null) {
      webApp.updateAppTheme(input.theme)
    }

    if (input.licenseType != null) {
      webApp.updateLicense(input.licenseType)
    }

    if (input.icon != null) {
      val uploadedIcon = storage.upload(input.icon)
      webApp.updateIcon(uploadedIcon.url)
    }

    if (input.splash != null) {
      val uploadedSplash = storage.upload(input.splash)
      webApp.updateIcon(uploadedSplash.url)
    }

    repository.save(webApp)
    return UpdateWebAppOutput(
      status = true,
      message = "앱이 정상적으로 업데이트 되었습니다.",
    )
  }
}

data class UpdateWebAppInput(
  val id: Long,
  val userId: Long,
  val name: String?,
  val theme: AppTheme?,
  val licenseType: LicenseType?,
  val icon: MultipartFile?,
  val splash: MultipartFile?,
) : Input {
  override fun validate(): Boolean {
    if (id == 0L) {
      throw IllegalArgumentException("유효한 고유번호를 입력하세요.")
    }

    if (userId <= 0L) {
      throw IllegalArgumentException("잘못된 유저정보 입니다.")
    }

    return true
  }
}

data class UpdateWebAppOutput(
  val status: Boolean,
  val message: String = "",
) : Output
