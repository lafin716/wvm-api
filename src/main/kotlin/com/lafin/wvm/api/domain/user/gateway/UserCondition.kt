package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.domain.gateway.Condition
import com.lafin.wvm.api.shared.type.AppPlatform

data class UserCondition (
  val id: Long? = 0L,
  val email: Email? = null,
  val name: String? = null,
  val page: Int = 0,
  val size: Int = 10,
) : Condition
