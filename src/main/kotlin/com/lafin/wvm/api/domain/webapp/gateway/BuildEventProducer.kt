package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface BuildEventProducer {
  fun requestBuild(app: WebApp): Boolean
}