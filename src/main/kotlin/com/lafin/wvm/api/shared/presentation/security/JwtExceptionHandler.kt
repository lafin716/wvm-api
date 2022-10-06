package com.lafin.wvm.api.shared.presentation.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.OutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtExceptionHandler : AuthenticationEntryPoint {

  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authException: AuthenticationException?
  ) {

    response?.contentType = MediaType.APPLICATION_JSON_VALUE
    response?.status = HttpStatus.UNAUTHORIZED.value()
    response?.sendError(HttpStatus.UNAUTHORIZED.value(), "ddd")
  }
}