package com.lafin.wvm.api.presentation.webapp.api.response

import com.lafin.wvm.api.presentation.webapp.api.dto.WebAppDto

data class WebAppListResponse(
  val status: Boolean,
  val message: String,
  val apps: List<WebAppDto>,
)

