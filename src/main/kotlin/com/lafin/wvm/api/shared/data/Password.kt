package com.lafin.wvm.api.shared.data

import java.util.regex.Pattern

data class Password(
  val value: String,
) {

  val minLength = 8
  val maxLength = 20

  fun validate(): Boolean {
    isAllowedCharacters()
    isValidCharacters()
    isTooManySameCharacters()

    return true
  }

  private fun isValidCharacters() {
    val regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$"
    val result = execute(value, regex)
    if (!result) {
      throw ValidException("비밀번호는 영문, 특수문자, 숫자를 포함한 8자 이상 20자 이하여야 합니다.")
    }
  }

  private fun isTooManySameCharacters() {
    val regex = "(\\w)\\1\\1"
    val result = execute(value, regex)
    if (result) {
      throw ValidException("비밀번호에 동일한 문자를 연속해서 사용할 수 없습니다.")
    }
  }

  private fun isAllowedCharacters() {
    val preFilterRegex = "\\W"
    val charFilterRegex = "[!@#$%^*+=-]"

    for (i in 0 until value.length) {
      val char = value[i].toString()
      if (execute(char, preFilterRegex)) {
        if (!execute(char, charFilterRegex)) {
          throw ValidException("비밀번호에 특수문자는 !@#\$^*+=-_만 사용 가능합니다.")
        }
      }
    }
  }

  private fun execute(value: String, regax: String): Boolean {
    val match = Pattern.compile(regax).matcher(value)
    return match.find()
  }
}
