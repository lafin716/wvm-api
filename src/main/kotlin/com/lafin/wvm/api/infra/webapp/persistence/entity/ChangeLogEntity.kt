package com.lafin.wvm.api.infra.webapp.persistence.entity

import com.lafin.wvm.api.shared.type.RoleType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "wvm_app_changelogs")
class ChangeLogEntity(
  _appId: Long,
  _userId: Long,
  _message: String,
) {

  @Id
  @GeneratedValue
  var id: Long? = null
  var appId: Long = _appId
  var userId: Long = _userId
  var message: String = _message
  var roleType: RoleType = RoleType.USER
  var createdAt: LocalDateTime = LocalDateTime.now()
}