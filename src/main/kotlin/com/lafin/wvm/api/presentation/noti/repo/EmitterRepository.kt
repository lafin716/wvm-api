package com.lafin.wvm.api.presentation.noti.repo

import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Component
class EmitterRepository {

  private val emitters = mutableMapOf<String, SseEmitter>()

  fun getCount() = emitters.size

  fun add(userId: String, emitter: SseEmitter) {
    emitters[userId] = emitter
  }

  fun getStartsWith(userId: String): Map<String, SseEmitter> {
    return emitters.filter { it.key.startsWith(userId) }
  }

  fun remove(userId: String) {
    emitters.remove(userId)
  }

  fun removeStartsWith(userId: String) {
    emitters.filter { it.key.startsWith(userId) }.forEach { emitters.remove(it.key) }
  }
}