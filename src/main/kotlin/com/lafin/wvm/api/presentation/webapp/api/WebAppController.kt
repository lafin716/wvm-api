package com.lafin.wvm.api.presentation.webapp.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class WebAppController {

  @GetMapping("/hi")
  fun hello(): String {
    return "WVM Hello";
  }
}