package com.lafin.wvm.api.presentation.user.api.response

data class SignInResponse(
  val result: Boolean,
  val message: String,
  val accessToken: String? = null,
  val refreshToken: String? = null,
)
