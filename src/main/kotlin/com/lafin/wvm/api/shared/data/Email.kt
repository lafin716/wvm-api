package com.lafin.wvm.api.shared.data

import java.util.regex.Pattern

data class Email(
  val value: String,
) {

  fun validate(): Boolean {
    val regax = "^(.+)@(.+)$"
    val pattern = Pattern.compile(regax)
    val matcher = pattern.matcher(value)

    return matcher.matches()
  }
}
