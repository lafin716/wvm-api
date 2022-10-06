package com.lafin.wvm.api.presentation.webapp.api.dto

import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

data class WebAppDto(
  val id: Long,
  var name: String,
  var initUrl: String,
  var platform: AppPlatform,
  var theme: AppTheme,
  var licenseType: LicenseType,
  var status: WebAppStatus,
  var buildStatus: BuildStatus,
  var deployStatus: DeployStatus,
  var createdAt: LocalDateTime,
  var updatedAt: LocalDateTime? = null,
  var lastBuiltAt: LocalDateTime? = null,
  var lastDeployedAt: LocalDateTime? = null,
  var deletedAt: LocalDateTime? = null,
)

