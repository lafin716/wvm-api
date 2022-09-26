package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.shared.domain.gateway.Condition
import com.lafin.wvm.api.shared.type.AppPlatform

data class WebAppCondition (
  val id: Long? = 0L,
  val userId: Long? = 0L,
  val name: String? = null,
  val platform: AppPlatform? = null,
  val page: Int = 0,
  val size: Int = 10,
) : Condition
