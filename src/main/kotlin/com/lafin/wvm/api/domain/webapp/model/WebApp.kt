package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

data class WebApp(
  val userId: Long,
  var name: String,
  var initUrl: String,
  var platform: AppPlatform,
  val id: Long = 0L,
  var theme: AppTheme = AppTheme.DEFAULT,
  var licenseType: LicenseType = LicenseType.FREE,
) {
  var status: WebAppStatus? = null
  var buildStatus: BuildStatus? = null
  var deployStatus: DeployStatus? = null
  var createdAt: LocalDateTime? = null
  var updatedAt: LocalDateTime? = null
  var lastBuiltAt: LocalDateTime? = null
  var lastDeployedAt: LocalDateTime? = null
  var deletedAt: LocalDateTime? = null
  var logs: MutableList<ChangeLog>? = null

  fun create() {
    status = WebAppStatus.CREATED
    createdAt = LocalDateTime.now()
    addLog("앱이 생성 되었습니다.")
  }

  fun ready() {
    status = WebAppStatus.READY
    buildStatus = BuildStatus.PREPARED
    updatedAt = LocalDateTime.now()
    addLog("빌드 준비가 완료 되었습니다.")
  }

  fun running() {
    updatedAt = LocalDateTime.now()
    if (status == WebAppStatus.RUNNING) {
      return
    }

    status = WebAppStatus.RUNNING
    addLog("앱이 운영처리 되었습니다.")
  }

  fun stopped() {
    if (status == WebAppStatus.STOP) {
      return
    }

    status = WebAppStatus.STOP
    updatedAt = LocalDateTime.now()
    addLog("앱이 정지 되었습니다.")
  }

  fun expired() {
    if (status == WebAppStatus.EXPIRED) {
      return
    }

    status = WebAppStatus.EXPIRED
    updatedAt = LocalDateTime.now()
    addLog("앱 사용기간이 만료 되었습니다.")
  }

  fun removed() {
    if (status == WebAppStatus.REMOVED) {
      return
    }

    status = WebAppStatus.REMOVED
    deletedAt = LocalDateTime.now()
    addLog("앱이 삭제 되었습니다.")
  }

  fun build(): Boolean {
    if ((status?.buildable ?: false) == false) {
      return false
    }

    buildStatus = BuildStatus.PENDING
    updatedAt = LocalDateTime.now()
    addLog("앱 빌드를 시작합니다.")
    return true
  }

  fun failBuild() {
    buildStatus = BuildStatus.ERROR
    updatedAt = LocalDateTime.now()
    addLog("앱 빌드가 정상적으로 완료되지 못했습니다.")
  }

  fun complateBuild() {
    buildStatus = BuildStatus.COMPLETE
    updatedAt = LocalDateTime.now()
    lastBuiltAt = LocalDateTime.now()
    addLog("앱 빌드가 정상적으로 완료되었습니다.")
  }

  fun deploy(): Boolean {
    if (buildStatus != BuildStatus.COMPLETE) {
      return false
    }

    deployStatus = DeployStatus.PENDING
    updatedAt = LocalDateTime.now()
    addLog("앱 배포를 시작합니다.")
    return true
  }

  fun failDeploy() {
    deployStatus = DeployStatus.ERROR
    updatedAt = LocalDateTime.now()
    addLog("앱 배포가 정상적으로 완료되지 못했습니다.")
  }

  fun complateDeploy() {
    deployStatus = DeployStatus.DEPLOYED
    updatedAt = LocalDateTime.now()
    lastDeployedAt = LocalDateTime.now()
    addLog("앱 배포가 정상적으로 완료되었습니다.")
  }

  fun updateAppName(name: String) {
    this.name = name
    addLog("앱 이름이 변경되었습니다.")
  }

  fun updateAppTheme(appTheme: AppTheme) {
    this.theme = appTheme
    addLog("앱 테마 정보가 변경되었습니다.")
  }

  fun updateLicense(licenseType: LicenseType) {
    this.licenseType = licenseType
    addLog("서비스 라이센스가 변경되었습니다.")
  }

  fun addLog(adminUserId: Long, message: String) {
    addLog(
      userId = adminUserId,
      message = message,
    )
  }

  private fun addLog(message: String, userId: Long = this.userId) {
    if (logs == null) {
      logs = mutableListOf()
    }

    logs!!.add(
      ChangeLog(
        id,
        userId,
        message,
      )
    )
  }
}