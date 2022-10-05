package com.lafin.wvm.api.domain.user.model

import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.data.ValidException
import com.lafin.wvm.api.shared.domain.Aggregate
import com.lafin.wvm.api.shared.status.UserStatus
import com.lafin.wvm.api.shared.type.RoleType
import java.time.LocalDateTime

data class User(
  var name: String,
  val email: Email,
  var password: Password,
  val role: RoleType = RoleType.USER,
  var status: UserStatus = UserStatus.PENDING,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime? = null,
  var removedAt: LocalDateTime? = null,
  var bannedAt: LocalDateTime? = null,
  var lastLoggedIn: LocalDateTime? = null,
  val id: Long? = null,
) : Aggregate {

  fun registed() {
    if (!email.validate()) {
      throw ValidException("이메일 형식이 아닙니다.")
    }
    createdAt = LocalDateTime.now()
    status = UserStatus.ACTIVE
  }

  fun activated() {
    status = UserStatus.ACTIVE
    updatedAt = LocalDateTime.now()
  }

  fun removed() {
    status = UserStatus.REMOVED
    removedAt = LocalDateTime.now()
  }

  fun sleep() {
    val limitDate = LocalDateTime.now().minusMinutes(1L)
    if (lastLoggedIn?.isAfter(limitDate) == true) {
      return
    }

    status = UserStatus.SLEEPING
    updatedAt = LocalDateTime.now()
  }

  fun banned() {
    status = UserStatus.REMOVED
    bannedAt = LocalDateTime.now()
  }

  fun updateName(name: String) {
    this.name = name
    updatedAt = LocalDateTime.now()
  }

  fun changePassword(password: Password) {
    password.validate()
    if (this.password == password) {
      throw ValidException("이전 비밀번호와 동일합니다.")
    }

    this.password = password
    updatedAt = LocalDateTime.now()
  }
}
