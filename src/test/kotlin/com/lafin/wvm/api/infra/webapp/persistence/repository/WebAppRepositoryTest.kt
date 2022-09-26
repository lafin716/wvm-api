package com.lafin.wvm.api.infra.webapp.persistence.repository

import com.lafin.wvm.api.infra.webapp.persistence.entity.WebAppEntity
import com.lafin.wvm.api.shared.status.BuildStatus
import com.lafin.wvm.api.shared.status.DeployStatus
import com.lafin.wvm.api.shared.status.WebAppStatus
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import java.time.LocalDateTime

@DataJpaTest
class WebAppRepositoryTest(
  @Autowired val repository: WebAppRepository,
) {

  @Test
  fun DB테스트() {

    repository.save(WebAppEntity(
      userId = 1L,
      name = "기본앱",
      initUrl = "https://naver.com",
      platform = AppPlatform.ANDROID,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
      status = WebAppStatus.RUNNING,
      buildStatus = BuildStatus.COMPLETE,
      deployStatus = DeployStatus.NOT_DEPLOYED,
      createdAt = LocalDateTime.now(),
      updatedAt = null,
      lastBuiltAt = null,
      lastDeployedAt = null,
      deletedAt = null,
    ))

    val result = repository.findAll();
    println(result)
  }

  @Test
  fun Pageable테스트()
  {
    bulkSaveApp(100)

    val pageable = PageRequest.of(1, 10)
    val result = repository.findAllByUserIdOrderByIdDesc(1L, pageable);
    println(result)
  }

  fun bulkSaveApp(count: Int) {
    for (i in 1..count) {
      saveApp()
    }
  }

  fun saveApp() {
    repository.save(WebAppEntity(
      userId = 1L,
      name = "기본앱",
      initUrl = "https://naver.com",
      platform = AppPlatform.ANDROID,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
      status = WebAppStatus.RUNNING,
      buildStatus = BuildStatus.COMPLETE,
      deployStatus = DeployStatus.NOT_DEPLOYED,
      createdAt = LocalDateTime.now(),
      updatedAt = null,
      lastBuiltAt = null,
      lastDeployedAt = null,
      deletedAt = null,
    ))
  }
}