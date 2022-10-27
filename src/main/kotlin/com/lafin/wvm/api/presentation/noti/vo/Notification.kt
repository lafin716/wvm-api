package com.lafin.wvm.api.presentation.noti.vo

data class Notification(
  val id: Long,
  val userId: Long,
  val title: String,
  val content: String,
  val createdAt: String
)
