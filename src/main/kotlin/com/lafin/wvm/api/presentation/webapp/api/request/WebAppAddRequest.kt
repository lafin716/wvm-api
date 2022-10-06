package com.lafin.wvm.api.presentation.webapp.api.request

data class WebAppAddRequest(
  val name: String,
  val initUrl: String,
  val theme: String,
  val platform: String,
  val licenseType: String,
)