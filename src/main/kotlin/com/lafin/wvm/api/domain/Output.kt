package com.lafin.wvm.api.domain

interface Output {
  val status: Boolean

  fun isOk(): Boolean {
    return status
  }
}