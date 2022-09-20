package com.lafin.wvm.api.shared.status

enum class WebAppStatus(
  val label: String,
  val buildable: Boolean,
) {
  CREATED("생성됨", false),
  READY("빌드준비완료", true),
  RUNNING("운영중", true),
  STOP("중지됨", false),
  EXPIRED("만료됨", false),
  REMOVED("삭제됨", false);
}