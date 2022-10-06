package com.lafin.wvm.api.shared.presentation.security

import com.lafin.wvm.api.shared.presentation.response.responseError
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtExceptionHandler : AuthenticationEntryPoint {

  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authException: AuthenticationException?
  ) {
    responseError(response!!, "로그인 정보가 없습니다.")
  }
}