package com.lafin.wvm.api.shared.presentation.config

import com.lafin.wvm.api.shared.presentation.security.JwtAuthenticationFilter
import com.lafin.wvm.api.shared.presentation.security.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig {

  fun jwtAuthenticationFilter(jwtProvider: JwtProvider): JwtAuthenticationFilter {
    return JwtAuthenticationFilter(jwtProvider)
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun filterChain(http: HttpSecurity, jwtProvider: JwtProvider): SecurityFilterChain {
    return http
      .formLogin()
      .disable()
      .csrf()
      .disable()
      .authorizeHttpRequests()
      .antMatchers("/api/**/user")
      .authenticated()
      .anyRequest()
      .permitAll()
      .and()
      .addFilterBefore(jwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
      .build()
  }
}