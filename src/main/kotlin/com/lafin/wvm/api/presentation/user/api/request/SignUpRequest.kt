package com.lafin.wvm.api.presentation.user.api.request

data class SignUpRequest(
  val name: String,
  val email: String,
  val password: String,
  val rePassword: String,
)
