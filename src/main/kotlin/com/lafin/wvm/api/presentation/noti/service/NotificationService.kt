package com.lafin.wvm.api.presentation.noti.service

import com.lafin.wvm.api.presentation.noti.repo.EmitterRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Service
class NotificationService(
  private val emitterRepository: EmitterRepository
) {

  private val log = LoggerFactory.getLogger(javaClass)

  private val timeout: Long = 60 * 60 * 1000L

  fun subscribe(userId: Long, lastEventId: String?): SseEmitter {
    val uuid = userId.toString() + "-" + System.currentTimeMillis()
    val emitter = SseEmitter(timeout)
    emitter.onCompletion {
      log.info("onCompletion")
      emitterRepository.remove(uuid)
    }
    emitter.onTimeout {
      log.info("onTimeout")
      emitterRepository.remove(uuid)
    }

    emitterRepository.add(uuid, emitter)
    sendToClient(uuid, emitter, "{\"message\":\"Hello You connected\"")

    return emitter
  }

  fun send(userId: Long, message: String) {
    val emitters = emitterRepository.getStartsWith(userId.toString())
    log.info("emitters size: ${emitters.size}")
    log.info("메시지: ${message}")
    emitters.forEach { (key, emitter) -> sendToClient(key, emitter, message) }
  }

  private fun sendToClient(key: String, emitter: SseEmitter, data: String) {
    try {
      emitter.send(SseEmitter.event()
        .id(key)
        .name("sse")
        .data(data)
        .build())
    } catch (e: Exception) {
      e.printStackTrace()
      log.info("send error")
      emitterRepository.remove(key)
    }
  }
}