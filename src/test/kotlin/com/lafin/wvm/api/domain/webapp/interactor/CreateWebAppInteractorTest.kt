package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppRepository
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class CreateWebAppInteractorTest {

  @Test
  fun 안드로이드_앱_신규생성() {
    val input = CreateWebAppInteractor.CreateWebAppInput(
      userId = 1L,
      name = "신규앱",
      initUrl = "http://fmlive2.shop",
      platform = AppPlatform.ANDROID,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
    )

    val result = CreateWebAppInteractor(repository = object: WebAppRepository {
      override fun save(webApp: WebApp): WebApp {
        var copyWebApp = WebApp(
          id = 1L,
          userId = webApp.userId,
          name = webApp.name,
          initUrl = webApp.initUrl,
          theme = webApp.theme,
          platform = webApp.platform,
          licenseType = webApp.licenseType,
        )
        return copyWebApp
      }

      override fun isDuplicateName(name: String): Boolean {
        return false
      }
    }).create(input)

    assertTrue(result.status)
    println(result)
  }

}