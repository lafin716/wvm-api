package com.lafin.wvm.api.infra.webapp.persistence.entity

import com.lafin.wvm.api.shared.infra.entity.PersistEntity
import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "wvm_apps")
data class WebAppEntity (
  @Id
  @GeneratedValue
  var id: Long? = null,
  var userId: Long,
  var name: String,
  var initUrl: String,
  var icon: String?,
  var splash: String?,
  var platform: AppPlatform,
  var version: String,
  var theme: AppTheme,
  var licenseType: LicenseType,
  var status: WebAppStatus,
  var buildStatus: BuildStatus,
  var deployStatus: DeployStatus,
  var createdAt: LocalDateTime,
  var updatedAt: LocalDateTime?,
  var lastBuiltAt: LocalDateTime?,
  var lastDeployedAt: LocalDateTime?,
  var deletedAt: LocalDateTime?,
) : PersistEntity {

  @OneToMany
  var logs: MutableList<ChangeLogEntity>? = null
}