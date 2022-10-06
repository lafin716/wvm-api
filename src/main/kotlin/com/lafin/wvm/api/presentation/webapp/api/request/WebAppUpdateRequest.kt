package com.lafin.wvm.api.presentation.webapp.api.request

import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType

data class WebAppUpdateRequest(
  val name: String?,
  val theme: AppTheme?,
  val licenseType: LicenseType?,
)
