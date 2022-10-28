package com.lafin.wvm.api.infra.webapp.producer.adapter

import com.lafin.wvm.api.domain.webapp.gateway.BuildEventProducer
import com.lafin.wvm.api.domain.webapp.model.WebApp
import org.springframework.stereotype.Component

@Component
class BuildProducerAdapter : BuildEventProducer {

  override fun requestBuild(app: WebApp): Boolean {
    TODO("Not yet implemented")
  }
}