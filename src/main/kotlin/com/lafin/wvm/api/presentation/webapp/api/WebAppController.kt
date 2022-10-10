package com.lafin.wvm.api.presentation.webapp.api

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.presentation.webapp.adapter.WebAppInteractorAdapter
import com.lafin.wvm.api.presentation.webapp.api.dto.WebAppDto
import com.lafin.wvm.api.presentation.webapp.api.request.WebAppAddRequest
import com.lafin.wvm.api.presentation.webapp.api.request.WebAppUpdateRequest
import com.lafin.wvm.api.presentation.webapp.api.response.*
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.presentation.ApiV1Controller
import com.lafin.wvm.api.shared.presentation.annotation.QueryStringResolver
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

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
  fun add(
    @RequestPart request: WebAppAddRequest,
    @RequestPart(required = false) icon: MultipartFile?,
    @RequestPart(required = false) splash: MultipartFile?,
    auth: Authentication
  ): ResponseEntity<WebAppAddResponse> {
    return adapter.add(request, icon, splash, auth.principal as String)
  }

  @PutMapping("/app/{id}")
  fun update(
    @PathVariable(name = "id") id: Long,
    @RequestPart request: WebAppUpdateRequest,
    @RequestPart(required = false) icon: MultipartFile?,
    @RequestPart(required = false) splash: MultipartFile?,
    auth: Authentication
  ): ResponseEntity<WebAppUpdateResponse> {
    return adapter.update(id, icon, splash, auth.principal as String, request)
  }

  @DeleteMapping("/app/{id}")
  fun delete(
    @PathVariable(name = "id") id: Long,
    auth: Authentication
  ): ResponseEntity<WebAppDeleteResponse> {
    return adapter.delete(id, auth.principal as String)
  }
}