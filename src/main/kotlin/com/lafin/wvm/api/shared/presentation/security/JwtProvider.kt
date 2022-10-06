package com.lafin.wvm.api.shared.presentation.security

import com.lafin.wvm.api.presentation.user.vo.DefaultUserDetails
import com.lafin.wvm.api.shared.presentation.property.SecurityProperty
import com.lafin.wvm.api.shared.presentation.service.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwt
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import java.util.*
import javax.annotation.PostConstruct

@Component
class JwtProvider(
  @Autowired private val securityProperty: SecurityProperty,
  @Autowired private val userService: UserService,
) {

  private var issuer: String? = null
  private var secretKey: String? = null
  private var expireTime: Date? = null
  private var refreshTime: Date? = null

  @PostConstruct
  fun init() {
    issuer = securityProperty.jwt.issuer
    secretKey = Base64Utils.encodeToString(securityProperty.jwt.secret.toByteArray())
    expireTime = Date(securityProperty.jwt.expireTime)
    refreshTime = Date(securityProperty.jwt.refreshTime)
  }

  fun getSigningKey(secretKey: String): String {
    return Base64Utils.encodeToString(secretKey.toByteArray())
  }

  fun createToken(email: String): String {
    val claims: Claims = Jwts.claims().setSubject(email)
    claims["email"] = email
    val now = Date()
    return Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(Date(now.time + expireTime!!.time))
      .signWith(SignatureAlgorithm.HS256, getSigningKey(securityProperty.jwt.secret))
      .compact()
  }

  fun getEmail(token: String) : String {
    val claims = getAllClaims(token)
    return claims["email"] as String
  }

  fun getAllClaims(token: String): Claims {
    return try {
      Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .body
    } catch (e: MalformedJwtException) {
      throw Exception("유효하지 않은 토큰입니다. :: ${token}")
    } catch (e: Exception) {
      e.printStackTrace()
      throw Exception("토큰 위변조가 감지되었습니다. :: ${token}")
    }
  }

  fun validateToken(token: String) : Boolean {
    val claims = getAllClaims(token)
    val expire = claims.expiration
    return expire.after(Date())
  }

  fun getAuthenticate(email: String): Authentication {
    val userDetails = userService.loadUserByUsername(email) as DefaultUserDetails

    return UsernamePasswordAuthenticationToken(
      userDetails.email,
      userDetails.password,
      userDetails.authorities
    )
  }
}