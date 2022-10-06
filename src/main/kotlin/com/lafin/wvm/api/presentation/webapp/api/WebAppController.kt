package com.lafin.wvm.api.presentation.webapp.api

import com.lafin.wvm.api.presentation.webapp.adapter.WebAppInteractorAdapter
import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebAppController(
  private val adapter: WebAppInteractorAdapter
) : ApiV1Controller {

  @GetMapping("/hi")
  fun hello(): String {
    return "WVM Hello";
  }
}