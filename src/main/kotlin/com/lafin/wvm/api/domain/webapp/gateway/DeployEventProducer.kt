package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface DeployEventProducer {
  fun requestDeploy(app: WebApp): Boolean
}