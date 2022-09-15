package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

class WebApp(
  val id: Long,
  val userId: Long,
  var name: String,
  var initUrl: String,
) {

  lateinit var status: WebAppStatus
  lateinit var appTheme: AppTheme
  lateinit var licenseType: LicenseType
  lateinit var createdAt: LocalDateTime
  lateinit var updatedAt: LocalDateTime
  var deletedAt: LocalDateTime? = null

  fun created() {
    status = WebAppStatus.CREATED
    appTheme = AppTheme.DEFAULT
    licenseType = LicenseType.FREE
    createdAt = LocalDateTime.now()
    updatedAt = LocalDateTime.now()
  }

  fun ready() {
    status = WebAppStatus.READY
    updatedAt = LocalDateTime.now()
  }

  fun expired() {
    status = WebAppStatus.EXPIRED
    updatedAt = LocalDateTime.now()
  }

  fun updateAppTheme(appTheme: AppTheme) {
    this.appTheme = appTheme
  }

  fun upgradeLicense(licenseType: LicenseType) {
    this.licenseType = licenseType
  }
}