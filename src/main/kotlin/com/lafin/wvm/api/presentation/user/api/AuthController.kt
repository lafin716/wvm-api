package com.lafin.wvm.api.presentation.user.api

import com.lafin.wvm.api.presentation.user.adapter.UserInteractorAdapter
import com.lafin.wvm.api.presentation.user.adapter.UserServiceAdapter
import com.lafin.wvm.api.presentation.user.api.request.SignInRequest
import com.lafin.wvm.api.presentation.user.api.request.SignUpRequest
import com.lafin.wvm.api.presentation.user.api.response.SignInResponse
import com.lafin.wvm.api.presentation.user.api.response.SignUpResponse
import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
  private val adapter: UserInteractorAdapter
) : ApiV1Controller {

  @PostMapping("/auth/signup")
  fun signUp(@RequestBody request: SignUpRequest) : ResponseEntity<SignUpResponse> {
    return ResponseEntity.ok(adapter.signUp(request))
  }

  @PostMapping("/auth/signin")
  fun signIn(@RequestBody request: SignInRequest) : ResponseEntity<SignInResponse> {
    return ResponseEntity.ok(adapter.signIn(request))
  }
}