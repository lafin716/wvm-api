package com.lafin.wvm.api.shared.data

class ValidException(
  override val message: String,
): RuntimeException(message)