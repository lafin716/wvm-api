package com.lafin.wvm.api.domain.webapp.model

import org.hibernate.usertype.UserType
import java.time.LocalDateTime

data class ChangeLog(
  val id: Long,
  val userId: Long,
  val userType: UserType,
  val message: String,
  val createdAt: LocalDateTime,
)