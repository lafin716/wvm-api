package com.lafin.wvm.api.shared.presentation.response

data class ErrorResponse(
  val status: Boolean = false,
  val message: String,
)
