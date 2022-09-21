package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface BuildGateway {
  fun requestBuild(app: WebApp): Boolean
}