package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.Input
import com.lafin.wvm.api.domain.Output
import com.lafin.wvm.api.domain.webapp.usecase.CreateWebAppUseCase
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType

class CreateWebAppInteractor(
  val input: Input
) : CreateWebAppUseCase {

  override fun create(input: Input): Output {

    return CreateWebbAppOutput()
  }

  class CreateWebAppInput(
    val name: String,
    val initUrl: String,
    val theme: AppTheme,
    val licenseType: LicenseType,
  ) : Input {
    override fun validate() {

    }
  }

  class CreateWebbAppOutput : Output {

  }
}