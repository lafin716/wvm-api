package com.lafin.wvm.api.infra.webapp.persistence.adapter

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.infra.webapp.persistence.convert.WebAppConverter
import com.lafin.wvm.api.infra.webapp.persistence.repository.WebAppRepository
import com.lafin.wvm.api.shared.type.AppPlatform
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@DisplayName("웹앱_어댑터")
class WebAppPersistenceAdapterTest (
  @Autowired val repository: WebAppRepository
) {

  private val webAppConverter: WebAppConverter = WebAppConverter()

  @InjectMocks
  val adapter: WebAppPersistenceAdapter = WebAppPersistenceAdapter(repository, webAppConverter)

  @Test
  fun 안드로이드_신규_추가() {
    val savedApp = adapter.save(createAndroidApp())
    println(savedApp)
    assertNotNull(savedApp)
    assertTrue((savedApp!!.id ?: 0L) > 0L)
  }

  @Test
  fun IOS_신규_추가() {
    val savedApp = adapter.save(createIosApp())
    println(savedApp)
    assertNotNull(savedApp)
    assertTrue((savedApp!!.id ?: 0L) > 0L)
  }

  @Test
  fun 앱이름_중복됨() {
    adapter.save(createApp(
      name = "중복앱",
      platform = AppPlatform.ANDROID,
    ))

    adapter.save(createApp(
      name = "중복앱",
      platform = AppPlatform.IOS,
    ))

    val result = adapter.isDuplicateName(
      WebAppCondition(
        userId = 1L,
        name = "중복앱",
        platform = AppPlatform.ANDROID,
      )
    )

    println(result)
    assertTrue(result)
  }

  @Test
  fun 앱이름_중복안됨() {
    adapter.save(createApp(
      name = "중복앱",
      platform = AppPlatform.IOS,
    ))

    val result = adapter.isDuplicateName(
      WebAppCondition(
        userId = 1L,
        name = "중복앱",
        platform = AppPlatform.ANDROID,
      )
    )

    println(result)
    assertFalse(result)
  }

  @Test
  fun 단일_앱_검색() {
    adapter.save(createAndroidApp())
    val app = adapter.getOne(WebAppCondition(
      userId = 1L,
      id = 1L,
    ))

    println(app)
    assertNotNull(app)
  }

  private fun createAndroidApp(): WebApp {
    return createApp(platform = AppPlatform.ANDROID)
  }

  private fun createIosApp(): WebApp {
    return createApp(platform = AppPlatform.IOS)
  }

  private fun createApp(
    name: String = "기본앱",
    initUrl: String = "https://naver.com",
    platform: AppPlatform
  ): WebApp {
    return WebApp(
      userId = 1L,
      name = name,
      initUrl = initUrl,
      platform = platform,
    )
  }
}