package com.lafin.wvm.api.domain.webapp.interactor

import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks

class CreateWebAppInteractorTest {

  private val mockRepository: WebAppPersistence = mockk()

  @InjectMocks
  val interactor: CreateWebAppInteractor = CreateWebAppInteractor(mockRepository)

  @Test
  fun 안드로이드_앱_신규_생성_성공() {
    val input = createAndroidAppInput()

    every { mockRepository.isDuplicateName(any()) } returns false
    every { mockRepository.save(any()) } returns createAndroidApp(1L)

    val result = interactor.execute(input)

    assertTrue(result.status)
    println(result.message)
  }

  @Test
  fun 안드로이드_앱_이름중복_실패() {
    val input = createAndroidAppInput()

    every { mockRepository.isDuplicateName(any()) } returns true

    val result = interactor.execute(input)

    assertFalse(result.status)
    println(result.message)
  }

  @Test
  fun 안드로이드_앱_생성_실패() {
    val input = createAndroidAppInput()

    every { mockRepository.isDuplicateName(any()) } returns false
    every { mockRepository.save(any()) } returns createAndroidApp(0L)

    val result = interactor.execute(input)

    assertFalse(result.status)
    println(result.message)
  }

  @Test
  fun iOS_앱_생성_성공() {
    val input = createIosAppInput()

    every { mockRepository.isDuplicateName(any()) } returns false
    every { mockRepository.save(any()) } returns createIosApp(1L)

    val result = interactor.execute(input)

    assertTrue(result.status)
    println(result.message)
  }

  @Test
  fun iOS_앱_생성_이름중복() {
    val input = createIosAppInput()

    every { mockRepository.isDuplicateName(any()) } returns true

    val result = interactor.execute(input)

    assertFalse(result.status)
    println(result.message)
  }

  @Test
  fun iOS_앱_생성_실패() {
    val input = createIosAppInput()

    every { mockRepository.isDuplicateName(any()) } returns false
    every { mockRepository.save(any()) } returns createIosApp(0L)

    val result = interactor.execute(input)

    assertFalse(result.status)
    println(result.message)
  }

  private fun createAndroidAppInput(): CreateWebAppInput {
    return CreateWebAppInput(
      userId = 1L,
      name = "신규앱",
      initUrl = "http://fmlive2.shop",
      platform = AppPlatform.ANDROID,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
    )
  }

  private fun createIosAppInput(): CreateWebAppInput {
    return CreateWebAppInput(
      userId = 1L,
      name = "신규앱",
      initUrl = "http://fmlive2.shop",
      platform = AppPlatform.IOS,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
    )
  }

  private fun createAndroidApp(id: Long = 0L): WebApp {
    return WebApp(
      id = id,
      userId = 1L,
      name = "신규앱",
      initUrl = "http://fmlive2.shop",
      platform = AppPlatform.ANDROID,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
    )
  }

  private fun createIosApp(id: Long = 0L): WebApp {
    return WebApp(
      id = id,
      userId = 1L,
      name = "신규앱",
      initUrl = "http://fmlive2.shop",
      platform = AppPlatform.IOS,
      theme = AppTheme.DEFAULT,
      licenseType = LicenseType.FREE,
    )
  }

}