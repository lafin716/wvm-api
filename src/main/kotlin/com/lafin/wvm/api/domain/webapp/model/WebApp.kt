package com.lafin.wvm.api.domain.webapp.model

import com.lafin.wvm.api.shared.consts.WebAppConst
import com.lafin.wvm.api.shared.data.Version
import com.lafin.wvm.api.shared.domain.Aggregate
import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import java.time.LocalDateTime

data class WebApp (
  val id: Long? = null,
  val userId: Long,
  var name: String,
  var initUrl: String,
  var platform: AppPlatform,
  var icon: String? = "",
  var splash: String? = "",
  var version: Version = Version.parse(WebAppConst.VERSION),
  var theme: AppTheme = AppTheme.DEFAULT,
  var licenseType: LicenseType = LicenseType.FREE,
  var status: WebAppStatus = WebAppStatus.CREATED,
  var buildStatus: BuildStatus = BuildStatus.NOT_PREPARED,
  var deployStatus: DeployStatus = DeployStatus.NOT_DEPLOYED,
  var createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime? = null,
  var lastBuiltAt: LocalDateTime? = null,
  var lastDeployedAt: LocalDateTime? = null,
  var deletedAt: LocalDateTime? = null,
  var logs: MutableList<ChangeLog>? = null,
) : Aggregate {

  fun create() {
    status = WebAppStatus.CREATED
    createdAt = LocalDateTime.now()
    updateBuildState()
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
    updateBuildState()
  }

  fun updateAppTheme(appTheme: AppTheme) {
    this.theme = appTheme
    addLog("앱 테마 정보가 변경되었습니다.")
    updateBuildState()
  }

  fun updateLicense(licenseType: LicenseType) {
    this.licenseType = licenseType
    addLog("서비스 라이센스가 변경되었습니다.")
    updateBuildState()
  }

  fun updateIcon(icon: String) {
    this.icon = icon
    addLog("아이콘이 업데이트 되었습니다.")
  }

  fun updateSplash(splash: String) {
    this.splash = splash
    addLog("스플래시 이미지가 업데이트 되었습니다.")
  }

  fun updateMajorVersion() {
    val prevVersion = version.toVersionString()
    version = version.updateMajor()
    addLog("앱 버전이 업데이트 되었습니다. ($prevVersion -> ${version.toVersionString()})")
    updateBuildState()
  }

  fun updateMinorVersion() {
    val prevVersion = version.toVersionString()
    version = version.updateMinor()
    addLog("앱 버전이 업데이트 되었습니다. ($prevVersion -> ${version.toVersionString()})")
    updateBuildState()
  }

  fun updatePatchVersion() {
    val prevVersion = version.toVersionString()
    version = version.updatePatch()
    addLog("앱 버전이 업데이트 되었습니다. ($prevVersion -> ${version.toVersionString()})")
    updateBuildState()
  }

  fun addLog(adminUserId: Long, message: String) {
    addLog(
      userId = adminUserId,
      message = message,
    )
  }

  fun updateBuildState() {
    if (isBuildable()) {
      buildStatus = getUpdatableBuildState(BuildStatus.PREPARED)
      updatedAt = LocalDateTime.now()
      addLog("빌드 준비가 완료 되었습니다.")
    }
  }

  fun updateBuildStatus(buildStatus: BuildStatus) {
    if (this.buildStatus == BuildStatus.COMPLETE) {
      return complateBuild()
    } else if (this.buildStatus == BuildStatus.ERROR) {
      return failBuild()
    }

    this.buildStatus = buildStatus
    updatedAt = LocalDateTime.now()
  }

  private fun isActive(): Boolean {
    return status == WebAppStatus.CREATED
        || status == WebAppStatus.RUNNING
  }

  private fun isBuildable(): Boolean {
    return isActive()
        && name.isNotBlank()
        && initUrl.isNotBlank()
        && icon?.isNotBlank() ?: false
        && splash?.isNotBlank() ?: false
  }

  private fun getUpdatableBuildState(state: BuildStatus): BuildStatus {
    if (buildStatus != BuildStatus.COMPLETE
      && buildStatus != BuildStatus.PENDING) {
      return state
    }

    return buildStatus
  }

  private fun addLog(message: String, userId: Long = this.userId) {
    logs = logs ?: mutableListOf()
    logs!!.add(
      ChangeLog(
        id!!,
        userId,
        message,
      )
    )
  }
}