package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.type.RoleType
import java.time.LocalDateTime

data class ChangeLog(
  val appId: Long,
  val userId: Long,
  val message: String,
  val id: Long? = null,
  val roleType: RoleType = RoleType.USER,
  val createdAt: LocalDateTime = LocalDateTime.now(),
)