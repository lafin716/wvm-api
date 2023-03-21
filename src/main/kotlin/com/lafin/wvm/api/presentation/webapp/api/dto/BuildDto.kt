package com.lafin.wvm.api.presentation.webapp.api.dto

import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

data class BuildDto(
  val id: Long,
  val status: BuildStatus,
  var name: String,
  val createdAt: LocalDateTime,
)

