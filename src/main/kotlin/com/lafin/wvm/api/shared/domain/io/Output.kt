package com.lafin.wvm.api.shared.domain.io

interface Output {
  val status: Boolean

  fun isOk(): Boolean {
    return status
  }
}