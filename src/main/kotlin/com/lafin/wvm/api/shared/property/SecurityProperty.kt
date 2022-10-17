package com.lafin.wvm.api.shared.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "security")
@ConstructorBinding
data class SecurityProperty(
  val jwt: JwtProperty,
) {

  data class JwtProperty(
    val issuer: String,
    val secret: String,
    val expireTime: Long,
    val refreshTime: Long,
  )
}
