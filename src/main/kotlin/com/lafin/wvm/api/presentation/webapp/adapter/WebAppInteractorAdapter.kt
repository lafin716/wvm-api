package com.lafin.wvm.api.presentation.webapp.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.interactor.*
import com.lafin.wvm.api.presentation.webapp.api.request.WebAppAddRequest
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppAddResponse
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppDto
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppListResponse
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.IllegalFormatCodePointException

@Service
class WebAppInteractorAdapter(
  private val getUserByEmailInteractor: GetUserByEmailInteractor,
  private val createWebAppInteractor: CreateWebAppInteractor,
  private val updateWebAppInteractor: UpdateWebAppInteractor,
  private val deleteWebAppInteractor: DeleteWebAppInteractor,
  private val getListWebAppInteractor: GetListWebAppInteractor,
  private val getWebAppInteractor: GetWebAppInteractor,
) {

  fun add(request: WebAppAddRequest, email: String): ResponseEntity<WebAppAddResponse> {
    val user = getUser(email)

    val theme = try {
      AppTheme.valueOf(request.theme)
    } catch (e: Exception) {
      throw Exception("테마정보가 잘못되었습니다.")
    }

    val platform = try {
      AppPlatform.valueOf(request.platform)
    } catch (e: Exception) {
      throw Exception("플랫폼 정보가 잘못되었습니다.")
    }

    val licenseType = try {
      LicenseType.valueOf(request.licenseType)
    } catch (e: Exception) {
      throw Exception("라이센스 정보가 잘못되었습니다.")
    }

    val output = createWebAppInteractor.execute(CreateWebAppInput(
      userId = user.id!!,
      name = request.name,
      initUrl = request.initUrl,
      theme = theme,
      platform = platform,
      licenseType = licenseType,
    ))
    if (!output.status) {
      return ResponseEntity.ok(WebAppAddResponse(
        status = false,
        message = output.message,
      ))
    }

    return ResponseEntity.ok(WebAppAddResponse(
      status = true,
      message = "새로운 앱이 추가되었습니다."
    ))
  }

  fun getList(email: String): ResponseEntity<WebAppListResponse> {
    val user = getUser(email)
    val appsOutput = getListWebAppInteractor.execute(GetListWebAppInput(
      userId = user.id ?: 0L,
    ))
    if (!appsOutput.status) {
      throw Exception(appsOutput.message)
    }

    return ResponseEntity.ok(
      WebAppListResponse(
      status = true,
      message = "앱 목록 조회 완료",
      apps = appsOutput.app.toList().map {
        webApp ->  WebAppDto(
          id = webApp.id!!,
          name = webApp.name,
          initUrl = webApp.initUrl,
          platform = webApp.platform,
          theme = webApp.theme,
          licenseType = webApp.licenseType,
          status = webApp.status,
          buildStatus = webApp.buildStatus,
          deployStatus = webApp.deployStatus,
          createdAt = webApp.createdAt,
          updatedAt = webApp.updatedAt,
          lastBuiltAt = webApp.lastBuiltAt,
          lastDeployedAt = webApp.lastDeployedAt,
          deletedAt = webApp.deletedAt,
        )
      }
    )
    )
  }

  private fun getUser(email: String): User {
    val userOutput = getUserByEmailInteractor.execute(
      GetUserByEmailInput(
        Email(email)
      )
    )
    if (!userOutput.status) {
      throw Exception(userOutput.message)
    }

    if (userOutput.user == null) {
      throw Exception("유저정보를 찾을 수 없습니다.")
    }

    return userOutput.user
  }
}