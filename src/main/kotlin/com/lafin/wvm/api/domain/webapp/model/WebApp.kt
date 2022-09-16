package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

class WebApp(
  val id: Long,
  val userId: Long,
  var name: String,
  var theme: AppTheme = AppTheme.DEFAULT,
  var platform: AppPlatform,
  var licenseType: LicenseType = LicenseType.FREE,
  var initUrl: String,
) {
  var status: WebAppStatus = WebAppStatus.CREATED
  var createdAt: LocalDateTime = LocalDateTime.now()
  var updatedAt: LocalDateTime? = null
  var logs: List<ChangeLog>? = null
  var deletedAt: LocalDateTime? = null

  fun ready() {
    status = WebAppStatus.READY
    updatedAt = LocalDateTime.now()
  }

  fun running() {
    status = WebAppStatus.RUNNING
    updatedAt = LocalDateTime.now()
  }

  fun stopped() {
    status = WebAppStatus.STOP
    updatedAt = LocalDateTime.now()
  }

  fun expired() {
    status = WebAppStatus.EXPIRED
    updatedAt = LocalDateTime.now()
  }

  fun removed() {
    status = WebAppStatus.REMOVED
    deletedAt = LocalDateTime.now()
  }

  fun updateAppTheme(appTheme: AppTheme) {
    this.theme = appTheme
  }

  fun upgradeLicense(licenseType: LicenseType) {
    this.licenseType = licenseType
  }
}