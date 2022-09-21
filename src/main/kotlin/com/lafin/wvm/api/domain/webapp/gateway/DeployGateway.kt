package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface DeployGateway {
  fun requestDeploy(app: WebApp): Boolean
}