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
import org.springframework.data.domain.Sort
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
//    bulkSaveApp(100)

    val pageable = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id"))
    val result = repository.findAllByUserIdAndPlatform(1L, null, pageable);
    println(result)
  }

  @Test
  fun 단일_검색() {
    saveApp()
    val app = repository.findById(1L)

    println(app)
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