package com.lafin.wvm.api.shared.presentation.security

import antlr.StringUtils
import org.apache.logging.log4j.util.Strings
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
  private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain
  ) {
    // 헤더에 Authorization이 있다면 가져온다.
    val authorizationHeader: String = request.getHeader("Authorization") ?: ""
    if (Strings.isBlank(authorizationHeader)) {
      return chain.doFilter(request, response)
    }
    // Bearer타입 토큰이 있을 때 가져온다.
    if (!authorizationHeader.contains("Bearer ")) {
      return chain.doFilter(request, response)
    }
    val token = authorizationHeader.substring("Bearer ".length) ?: return chain.doFilter(request, response)

    // 토큰 검증
    if (jwtProvider.validateToken(token)) {
      val email = jwtProvider.getEmail(token)
      val authentication = jwtProvider.getAuthenticate(email)
      SecurityContextHolder.getContext().authentication = authentication
    }

    chain.doFilter(request, response)
  }

  fun getCookie(request: HttpServletRequest?, key: String): Cookie? {
    return request?.cookies?.filter { cookie ->
      cookie.name.equals(key)
    }?.first()
  }
}