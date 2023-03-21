package com.lafin.wvm.api.presentation.webapp.api.response

import com.lafin.wvm.api.presentation.webapp.api.dto.BuildDto

data class GetBuildDetailResponse(
  val result: Boolean,
  val message: String,
  val data: BuildDto?,
)
