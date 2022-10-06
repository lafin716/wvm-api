package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.shared.domain.gateway.Condition
import com.lafin.wvm.api.shared.type.AppPlatform

data class WebAppCondition (
  val id: Long? = 0L,
  val userId: Long? = 0L,
  val name: String? = null,
  val platform: AppPlatform? = null,
  var page: Int = 0,
  var size: Int = 10,
) : Condition {
  init {
    if (page < 0) {
      page = 0
    }

    if (size < 0) {
      size = 10
    }
  }
}
