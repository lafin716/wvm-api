package com.lafin.wvm.api.presentation.user.adapter

import com.lafin.wvm.api.domain.webapp.interactor.CreateUserInput
import com.lafin.wvm.api.domain.webapp.interactor.CreateUserInteractor
import com.lafin.wvm.api.domain.webapp.interactor.SignInUserInput
import com.lafin.wvm.api.domain.webapp.interactor.SignInUserInteractor
import com.lafin.wvm.api.presentation.user.api.request.SignInRequest
import com.lafin.wvm.api.presentation.user.api.request.SignUpRequest
import com.lafin.wvm.api.presentation.user.api.response.SignInResponse
import com.lafin.wvm.api.presentation.user.api.response.SignUpResponse
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.presentation.security.JwtProvider
import org.springframework.stereotype.Service

@Service
class UserInteractorAdapter(
  private val jwtProvider: JwtProvider,
  private val signInUserInteractor: SignInUserInteractor,
  private val createUserInteractor: CreateUserInteractor,
) {

  fun signUp(request: SignUpRequest): SignUpResponse {
    if (request.password != request.rePassword) {
      return SignUpResponse(
        result = false,
        message = "비밀번호가 일치하지 않습니다.",
      )
    }

    val output = createUserInteractor.execute(CreateUserInput(
        name = request.name,
        email = Email(request.email),
        password = Password(request.password),
    ))
    if (!output.status) {
      return SignUpResponse(
        result = false,
        message = output.message,
      )
    }

    return SignUpResponse(
      result = true,
      message = output.message,
    )
  }

  fun signIn(request: SignInRequest): SignInResponse {
    val output = signInUserInteractor.execute(
      SignInUserInput(
        email = Email(request.email),
        password = Password(request.password),
      )
    )
    if (!output.status) {
      return SignInResponse(
        result = false,
        message = output.message
      )
    }

    val token = output.user?.email?.let { jwtProvider.createToken(email = it.value) }
    return SignInResponse(
      result = true,
      message = "토큰이 생성되었습니다.",
      accessToken = token,
    )
  }
}