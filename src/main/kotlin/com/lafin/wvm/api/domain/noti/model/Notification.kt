package com.lafin.wvm.api.domain.noti.model

import com.lafin.wvm.api.shared.domain.Aggregate
import java.time.LocalDateTime

data class Notification(
  val title: String,
  val content: String,
  val createdAt: LocalDateTime? = LocalDateTime.now(),
  val id: Long? = 0L,
  val userId: Long? = 0L,
) : Aggregate