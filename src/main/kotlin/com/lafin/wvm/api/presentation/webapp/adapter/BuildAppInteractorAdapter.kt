package com.lafin.wvm.api.presentation.webapp.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.interactor.CreateBuildWebAppInput
import com.lafin.wvm.api.domain.webapp.interactor.CreateBuildWebAppInteractor
import com.lafin.wvm.api.domain.webapp.interactor.GetUserByEmailInput
import com.lafin.wvm.api.domain.webapp.interactor.GetUserByEmailInteractor
import com.lafin.wvm.api.domain.webapp.usecase.CreateBuildWebAppUseCase
import com.lafin.wvm.api.presentation.webapp.api.response.CreateBuildResponse
import com.lafin.wvm.api.presentation.webapp.api.response.GetBuildDetailResponse
import com.lafin.wvm.api.shared.data.Email
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class BuildAppInteractorAdapter(
  private val createBuildWebAppInteractor: CreateBuildWebAppInteractor,
  private val getUserByEmailInteractor: GetUserByEmailInteractor,
) {

  fun getBuildDetail(id: Long, email: String): ResponseEntity<GetBuildDetailResponse> {

    return ResponseEntity.ok(
      GetBuildDetailResponse(
        result = true,
        message = "빌드 상세정보를 가져왔습니다.",
        data = null,
      )
    )
  }

  fun requestBuild(id: Long, email: String): ResponseEntity<CreateBuildResponse> {
    val user = getUser(email)
    val output = createBuildWebAppInteractor.execute(
      CreateBuildWebAppInput(
        id = id,
        userId = user.id!!,
      )
    )
    if (!output.status) {
      return ResponseEntity.badRequest().body(
        CreateBuildResponse(
          result = false,
          message = output.message,
        )
      )
    }

    return ResponseEntity.ok(
      CreateBuildResponse(
        result = true,
        message = "빌드 요청이 완료되었습니다.",
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