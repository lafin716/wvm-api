package com.lafin.wvm.api.domain.noti.model

import com.lafin.wvm.api.shared.domain.Aggregate
import com.lafin.wvm.api.shared.type.NotificationType
import java.time.LocalDateTime

data class Notification(
  val title: String,
  val content: String,
  val type: NotificationType,
  val id: Long? = 0L,
  val userId: Long? = 0L,
  val createdAt: LocalDateTime? = LocalDateTime.now(),
) : Aggregate