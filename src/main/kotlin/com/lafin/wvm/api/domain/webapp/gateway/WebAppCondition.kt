package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.shared.domain.gateway.Condition

data class WebAppCondition(
  val id: Long? = 0L,
  val userId: Long? = 0L,
  val name: String? = null,
) : Condition
