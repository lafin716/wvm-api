package com.lafin.wvm.api.shared.presentation.security

import com.lafin.wvm.api.shared.presentation.response.responseError
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.SignatureException
import org.apache.logging.log4j.util.Strings
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
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

    try {
      // 토큰 검증
      if (jwtProvider.validateToken(token)) {
        val email = jwtProvider.getEmail(token)
        val authentication = jwtProvider.getAuthenticate(email)
        SecurityContextHolder.getContext().authentication = authentication
      }
      chain.doFilter(request, response)
    } catch (e: AccessDeniedException) {
      responseError(response, e.message ?: "인증 정보가 없습니다.")
    } catch (e: ExpiredJwtException) {
      responseError(response, e.message ?: "토큰이 만료되었습니다.")
    } catch (e: SignatureException) {
      responseError(response, e.message ?: "유효하지 않은 토큰입니다.")
    } catch (e: Exception) {
      e.printStackTrace()
      responseError(response, e.message ?: "알 수 없는 에러입니다.")
    }
  }
}