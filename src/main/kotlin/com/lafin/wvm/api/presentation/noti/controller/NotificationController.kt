package com.lafin.wvm.api.presentation.noti.controller

import com.lafin.wvm.api.presentation.noti.service.NotificationService
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/subscribe")
class NotificationController(
  private val notificationService: NotificationService
) {

  @GetMapping("/push/{userId}")
  fun publish(
    @PathVariable userId: Long
  ) : String {
    notificationService.send(userId, "이벤트 받아라~")
    return "ok"
  }

  @GetMapping("/user/{userId}", produces = ["text/event-stream"])
  fun subscribe(
    @PathVariable userId: Long,
    @RequestHeader(value = "Last-Event-ID", required = false) lastEventId: String?
  ): SseEmitter {
    return notificationService.subscribe(userId, lastEventId)
  }
}