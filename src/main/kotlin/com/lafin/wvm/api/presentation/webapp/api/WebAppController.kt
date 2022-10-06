package com.lafin.wvm.api.presentation.webapp.api

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.presentation.webapp.adapter.WebAppInteractorAdapter
import com.lafin.wvm.api.presentation.webapp.api.dto.WebAppDto
import com.lafin.wvm.api.presentation.webapp.api.request.WebAppAddRequest
import com.lafin.wvm.api.presentation.webapp.api.request.WebAppUpdateRequest
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppAddResponse
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppListResponse
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppResponse
import com.lafin.wvm.api.presentation.webapp.api.response.WebAppUpdateResponse
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import com.lafin.wvm.api.shared.presentation.annotation.QueryStringResolver
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WebAppController(
  private val adapter: WebAppInteractorAdapter
) : ApiV1Controller {

  @GetMapping("/app")
  fun list(@QueryStringResolver condition: WebAppCondition, auth: Authentication): ResponseEntity<WebAppListResponse> {
    return adapter.getList(auth.principal as String, condition)
  }

  @GetMapping("/app/{id}")
  fun get(@PathVariable(name = "id") id: Long, auth: Authentication): ResponseEntity<WebAppResponse> {
    return adapter.get(id, auth.principal as String)
  }

  @PostMapping("/app")
  fun add(@RequestBody request: WebAppAddRequest, auth: Authentication): ResponseEntity<WebAppAddResponse> {
    return adapter.add(request, auth.principal as String)
  }

  @PutMapping("/app/{id}")
  fun update(
    @PathVariable(name = "id") id: Long,
    @RequestBody request: WebAppUpdateRequest,
    auth: Authentication
  ): ResponseEntity<WebAppUpdateResponse> {
    return adapter.update(id, auth.principal as String, request)
  }
}