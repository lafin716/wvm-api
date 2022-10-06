package com.lafin.wvm.api.presentation.webapp.api.response

import com.lafin.wvm.api.presentation.webapp.api.dto.WebAppDto

data class WebAppResponse(
  val status: Boolean,
  val message: String,
  val app: WebAppDto? = null,
)
