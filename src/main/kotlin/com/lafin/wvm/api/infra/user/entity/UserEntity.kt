package com.lafin.wvm.api.infra.user.entity

import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.infra.entity.PersistEntity
import com.lafin.wvm.api.shared.status.UserStatus
import com.lafin.wvm.api.shared.type.RoleType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "wvm_users")
data class UserEntity(
  var name: String,
  val email: String,
  var password: String,
  val role: RoleType = RoleType.USER,
  var status: UserStatus = UserStatus.PENDING,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime? = null,
  var removedAt: LocalDateTime? = null,
  var bannedAt: LocalDateTime? = null,
  var lastLoggedIn: LocalDateTime? = null,
) : PersistEntity {

  @Id
  @GeneratedValue
  val id: Long? = null
}