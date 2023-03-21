package com.lafin.wvm.api.presentation.webapp.api

import com.lafin.wvm.api.presentation.webapp.adapter.BuildAppInteractorAdapter
import com.lafin.wvm.api.presentation.webapp.api.response.CreateBuildResponse
import com.lafin.wvm.api.presentation.webapp.api.response.GetBuildDetailResponse
import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BuildController(
  private val adapter: BuildAppInteractorAdapter
): ApiV1Controller {

  @GetMapping("/build/{id}")
  fun detail(@PathVariable id: Long, auth: Authentication): ResponseEntity<GetBuildDetailResponse> {
    return adapter.getBuildDetail(id, auth.principal as String)
  }

  @PostMapping("/build/{id}")
  fun requestBuild(@PathVariable id: Long, auth: Authentication): ResponseEntity<CreateBuildResponse> {
    return adapter.requestBuild(id, auth.principal as String)
  }
}