package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.type.UserType
import java.time.LocalDateTime

data class ChangeLog(
  val appId: Long,
  val userId: Long,
  val message: String,
  val id: Long = 0L,
  val userType: UserType = UserType.USER,
  val createdAt: LocalDateTime = LocalDateTime.now(),
)