package com.lafin.wvm.api.presentation.user.api.request

data class SignInRequest(
  val email: String,
  val password: String,
)
