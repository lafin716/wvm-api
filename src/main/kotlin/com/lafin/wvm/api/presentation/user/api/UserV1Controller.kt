package com.lafin.wvm.api.presentation.user.api

import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/user")
class UserController : ApiV1Controller() {

  @GetMapping("/hi")
  fun test(): ResponseEntity<String> {
    return ResponseEntity.ok("hi")
  }
}